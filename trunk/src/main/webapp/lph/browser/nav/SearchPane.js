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
                
        // The data store containing the list of states
		this.comboStore = Ext.create('Ext.data.Store', {
		    fields: ['level', 'name'],
		    data : [
		        {"level": 0, "name":"All"},
		        {"level": 1, "name":"Category"},
		        {"level": 2, "name":"Main Class"},
		        {"level": 3, "name":"Sub Class"},
		        {"level": 4, "name":"Species"},
		        {"level": 5, "name":"FA Scan Species"},
		        {"level": 6, "name":"Sub Species"},
		        {"level": 7, "name":"Isomer"}
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
    	//console.info(combo, records, eOpts);
    	
    	console.info(records);
    	
    	//Query the path from the tree root to the selected item
    	
    	//Set the path elements in the tree structure
    	
    	//Expand all of them
    	
    	//Scroll to the 'selected by the user in the search box'
    	
    	//Select the item (let the manager do its work)
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