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
    height 	: 40,
    margins	: '5 5 5 5',
    frame	: true,
    border	: false,
    example : 'PC 36:2',
    
    constructor: function(config) {
    	this.callParent(arguments);
        this.initConfig(config);

        this.addEvents({
        	itemSelected : true
        });

        this.container = Ext.create('Ext.form.FieldContainer',{
        	layout	: 'hbox',
        	border	: false,
	        defaults: {
	            hideLabel   : true,
                border      : false,
                margins     : '3 0 3 0'
	        }
        });
        
        this.ds = Ext.create('Ext.data.Store', {
	        model    : 'SearchData',
            pageSize : 10
	    });
	    
	    //This is adding the type parameter to the query taking into account
	    //the selected item in the type combo box
	    this.ds.addListener("beforeLoad", function(store, operation, opts){
            Ext.Ajax.abortAll(); //TODO: Replace abortAll by abort(currentQueryId) -> Needed, find how to get the currentQueryId :D
	    	store.getProxy().extraParams = {
	    		query : this.search.getValue(),
	    		type  : this.combo.getValue()
	    	};
	    }, this);

        this.search = Ext.create('Ext.form.field.ComboBox', {
        	store		 : this.ds,
            border		 : false,
            typeAhead	 : false,
            hideLabel	 : true,
            hideTrigger	 : true,
            //triggerAction: 'all',
            flex		 : 1,
            emptyText	 : 'Search... (Example: ' + this.example + ')',
            tooltip      : 'Live search by name, minumum 4 characters',
			pageSize	 : 10,
			displayField : 'name',
            //queryDelay   : 2000,
            listConfig	 : {
                loadingText : 'Searching...',
                emptyText   : 'No matching lipids found.',
                // Custom rendering template for each item
                getInnerTpl : function(name) {
                    return  '<div>' +
                            '   <div class="{type}-{cls}" />' +
                            '   <div class="list-title">Name: {name}</div>' +
                            '   <div class="list-footer">Identified: {identified}</div>' +
                            '</div>';
                }
            },
            listeners   : {
            	scope	 : this,
            	'select' : this.itemSelected,
                'focus'  : this.onFocus,
            }
        });
        this.search.addListener('render', this._onItemRender)
        this.container.add(this.search);
                
        // The data store containing the list of structure hierarchy levels
		this.comboStore = Ext.create('Ext.data.Store', {
            model   : "LipidLevels",
		    data    : [
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
		    value			: this.comboStore.getAt(4).get('level'),
            tooltip         : 'Search by specific level of structure hierarchy or by all'
		});
        this.combo.addListener('render', this._onItemRender)
        this.container.add(this.combo);

        this.add(this.container);

        return this;
    },
    
    itemSelected: function(combo, records, eOpts){
        var record = records[0];
        this.fireEvent('itemSelected', record);
    },

    onFocus: function(field, ops){
        if(Ext.isEmpty(this.focusedBefore) && Ext.isEmpty(field.getValue())){
            field.emptyText = 'Search...';
            field.setValue(this.example);
            field.doQueryTask.delay(field.queryDelay);
            this.focusedBefore = true;
        }
        field.selectText();
    },

    _onItemRender: function(c){
        if(Ext.isEmpty(c.tooltip)) return;
        Ext.QuickTips.register({
            target  : c.getEl(),
            text    : c.tooltip,
            title   : c.fieldLabel
        });
    }
});

Ext.define("LipidLevels",{
    extend: 'Ext.data.Model',
    fields: ['level', 'name']
});

Ext.define("SearchData", {
    extend: 'Ext.data.Model',
    proxy: {
        type    : 'ajax',
        url     : 'service/utils/search',
        timeout : 120000,
        batchActions : false,
        reader  : {
            type          : 'json',
            root          : 'list',
            totalProperty : 'totalCount'
        }
    },

    fields: [
        {name: 'itemId', mapping: 'itemId'},
        {name: 'name', mapping: 'name'},
        {name: 'identified', mapping: 'identified'},
        {name: 'cls', mapping: 'identified', convert: function(identified){return identified?"identified-24":"unidentified-24";}},
        {name: 'type', mapping: 'type'}
    ]
});