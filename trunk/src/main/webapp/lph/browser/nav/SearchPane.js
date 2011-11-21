/*
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date August 2011
 *
 *   The SearchPane contains a google style live search of the database with suggestions. When 4 or more characters are
 *   typed into the search box a search is done and results return. Results are ordered by whether or not they have
 *   been identified. The search can also be configured to look at a particular hierarchy level of the database if
 *   for instance the user was only interested in sub species, via the combo box.
 *
*/

Ext.define('lph.browser.nav.SearchPane', {
	/* Begin Definitions */
    extend	: 'Ext.form.Panel',
    
    region	: 'north',
    layout	: 'fit',
    //title	: 'Search',
    height 	: 35,
    margins	: '5 5 5 5',
    frame	: true,
    border	: false,
    
    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);

        this.addEvents({
        	pathsFound	: true
        });

        this.container = Ext.create('Ext.form.FieldContainer',{
        	layout	: 'hbox',
        	border	: false,
	        defaults: {
	            hideLabel: true
	        }
        });
        
        this.ds = Ext.create('Ext.data.Store', {
	        pageSize: 10,
	        model: 'Post'
	    });
	    
	    //This is adding the type parameter to the query taking into account
	    //the selected item in the type combo box
	    this.ds.addListener("beforeLoad", function(store, operation, opts){
	    	store.getProxy().extraParams = {
	    		query : this.search.getValue(),
	    		type : this.combo.getValue()
	    	};
	    }, this);
	    
        this.search = Ext.create('Ext.form.field.ComboBox', {
        	store		 : this.ds,
            border		 : false,
            typeAhead	 : false,
            hideLabel	 : true,
            hideTrigger	 : true,
            flex		 : 1,
            emptyText	 : 'Search...',
			pageSize	 : 10,
			displayField : 'name',
            listConfig	 : {
                loadingText: 'Searching...',
                emptyText: 'No matching lipids found.',

                // Custom rendering template for each item
                getInnerTpl: function() {
                	return '<span>' +
                			'	<b>Name: {name}</b><br>' +
                			'	Identified: {identified}' +
                			'</span>';
                    /*
                    return '<a class="search-item" href="http://www.sencha.com/forum/showthread.php?t={topicId}&p={id}">' +
                        '<h3><span>{[Ext.Date.format(values.lastPost, "M j, Y")]}<br />by {author}</span>{title}</h3>' +
                        '{excerpt}' +
                    '</a>';
                    */
                }
            },
            listeners: {
            	scope		: this,
            	'select'	: this.itemSelected
            }
        });
        
        this.container.add(this.search);
                
        // The data store containing the list of structure hierarchy levels
		this.comboStore = Ext.create('Ext.data.Store', {
		    fields: ['level', 'name'],
		    data : [
		        {"level": "all", "name":"All"},
		        {"level": "category", "name":"Category"},
		        {"level": "mainClass", "name":"Main Class"},
		        {"level": "subClass", "name":"Sub Class"},
		        {"level": "specie", "name":"Species"},
		        {"level": "faScanSpecie", "name":"FA Scan Species"},
		        {"level": "subSpecie", "name":"Sub Species"},
		        {"level": "isomer", "name":"Isomer"}
		    ]
		});

        
        this.combo = Ext.create('Ext.form.field.ComboBox',{
        	width			: 120,
		    store			: this.comboStore,
		    queryMode		: 'local',
		    displayField	: 'name',
		    valueField		: 'level',
		    forceSelection	: true,
		    value			: this.comboStore.getAt(0).get('level')
		});
        this.container.add(this.combo);
        
        /*this.submit = Ext.create('Ext.Button',{
        	text : 'S'
        });
        this.container.add(this.submit);*/
        
        this.add(this.container);
        
        return this;
    },
    
    itemSelected: function(combo, records, eOpts){
        var record = records[0];
        Ext.Ajax.request({
            url     : '/service/utils/pathto',
            method  : 'GET',
            params  : {
                itemId      : record.get("itemId"),
                name        : record.get("name"),
                identified  : record.get("identified"),
                type        : record.get("type")
            },
            success : this.setPathToItems,
            scope   : this
        });

    },

    setPathToItems: function(response){
        var resp = Ext.decode(response.responseText);
        this.fireEvent('pathsFound', {'paths' : resp.paths});
    }
});


Ext.define("Post", {
    extend: 'Ext.data.Model',
    proxy: {
        type: 'jsonp',
        url : 'service/utils/search',
        reader: {
            type: 'json',
            root: 'list',
            totalProperty: 'totalCount'
        }
    },

    fields: [
        {name: 'itemId', mapping: 'itemId'},
        {name: 'name', mapping: 'name'},
        {name: 'identified', mapping: 'identified'},
        {name: 'type', mapping: 'type'}
    ]
});