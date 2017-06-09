Ext.data.JsonP.mvc_pt2({"guide":"<h1>Architecting Your App in Ext JS 4, Part 2</h1>\n\n<p>In the previous Ext JS Architecture article, we explored how to architect a Pandora-style application using Ext JS. We took a look at the Model-View-Controller architecture and how to apply it to a relatively complex UI application that had multiple views and models. In this article, we’re going to move beyond architecting the application visually, and explore how to design and code the controllers and models, starting with Ext.application and the Viewport class.</p>\n\n<p>Let’s just jump in and start writing the application.</p>\n\n<h2>Defining our application</h2>\n\n<p>In Ext JS 3, the Ext.onReady method was the entry point into your application, and the developer had to come up with an application architecture. In Ext JS 4, we have an introduced an MVC-like pattern. This pattern helps you to follow best practices when creating your applications.</p>\n\n<p>The entry point into an application written with the new MVC package requires that you use the Ext.application method. This method will create an <a href=\"#!/api/Ext.app.Application\" rel=\"Ext.app.Application\" class=\"docClass\">Ext.app.Application</a> instance for you and will fire the launch method as soon as the page is ready. This essentially replaces the need to use Ext.onReady while adding new functionality such as automatically creating a viewport and setting up your namespace.</p>\n\n<h3><code>app/Application.js</code></h3>\n\n<pre><code>Ext.application({\n    name: 'Panda',\n    autoCreateViewport: true,\n    launch: function() {\n        // This is fired as soon as the page is ready\n    }\n});\n</code></pre>\n\n<p>The name configuration causes a new namespace to be created. All our views, models, stores and controllers will live in this namespace. By setting autoCreateViewport to true, the framework will, by convention, include the app/view/Viewport.js file. In this file, a class should be defined with the name Panda.view.Viewport, matching the namespace that was specified by the name configuration of your application.</p>\n\n<h2>The Viewport class</h2>\n\n<p>When we looked at which views we needed for our UI, we were very focused on the individual parts. The Viewport of an application acts as the glue for these individual parts. It loads the required views and defines the configuration needed to achieve your app’s overall layout. We have found that progressively defining your views and adding them to the viewport is the fastest way to create the base structure of your UI.</p>\n\n<p>It is important during this process to focus on scaffolding your views and not on the individual views themselves. It’s almost like sculpting. We start by creating the very rough shapes of our views and add more detail to them later.</p>\n\n<h2>Creating the building blocks</h2>\n\n<p>Leveraging the work we already did in the previous article, we are able to define many of the views at once.</p>\n\n<p><p><img src=\"guides/mvc_pt2/balanced.png\" alt=\"\"></p></p>\n\n<h3><code>app/view/NewStation.js</code></h3>\n\n<pre><code>Ext.define('Panda.view.NewStation', {\n    extend: 'Ext.form.field.ComboBox',\n    alias: 'widget.newstation',\n    store: 'SearchResults',\n    ... more configuration ...\n});\n</code></pre>\n\n<h3><code>app/view/SongControls.js</code></h3>\n\n<pre><code>Ext.define('Panda.view.SongControls', {\n    extend: 'Ext.Container',\n    alias: 'widget.songcontrols',\n    ... more configuration ...\n});\n</code></pre>\n\n<h3><code>app/view/StationsList</code></h3>\n\n<pre><code>Ext.define('Panda.view.StationsList', {\n    extend: 'Ext.grid.Panel',\n    alias: 'widget.stationslist',\n    store: 'Stations',\n    ... more configuration ...\n});\n</code></pre>\n\n<h3><code>app/view/RecentlyPlayedScroller.js</code></h3>\n\n<pre><code>Ext.define('Panda.view.RecentlyPlayedScroller', {\n    extend: 'Ext.view.View',\n    alias: 'widget.recentlyplayedscroller',\n    itemTpl: '&lt;div&gt;&lt;/div&gt;',\n    store: 'RecentSongs',\n    ... more configuration ...\n});\n</code></pre>\n\n<h3><code>app/view/SongInfo.js</code></h3>\n\n<pre><code>Ext.define('Panda.view.SongInfo', {\n    extend: 'Ext.panel.Panel',\n    alias: 'widget.songinfo',\n    tpl: '&lt;h1&gt;About &lt;/h1&gt;&lt;p&gt;&lt;/p&gt;',\n    ... more configuration ...\n});\n</code></pre>\n\n<p>We have left out some of the configuration here since component configurations are not in the scope of this article.</p>\n\n<p>In the above configurations, you’ll notice that we have three stores configured. These map to the store names prepared in the previous article. At this point we’ll go ahead and create our stores.</p>\n\n<p><p><img src=\"guides/mvc_pt2/stores.png\" alt=\"\"></p></p>\n\n<h2>The models and stores</h2>\n\n<p>Often, it is useful to start with static json files containing mock data to act as our server side. Later, we can use these static files as a reference when we actually implement a dynamic server side.</p>\n\n<p>For our app, we decided to use two models, Station and Song. We also need three stores using these two models that will be bound to our data components. Each store will load its data from the server side. The mock data files would look something like the following.</p>\n\n<h2>Static data</h2>\n\n<h3><code>data/songs.json</code></h3>\n\n<pre><code>{\n    'success': true,\n    'results': [\n        {\n            'name': 'Blues At Sunrise (Live)',\n            'artist': 'Stevie Ray Vaughan',\n            'album': 'Blues At Sunrise',\n            'description': 'Description for Stevie',\n            'played_date': '1',\n            'station': 1\n        },\n        ...\n    ]\n}\n</code></pre>\n\n<h3><code>data/stations.json</code></h3>\n\n<pre><code>{\n    'success': true,\n    'results': [\n        {'id': 1, 'played_date': 4, 'name': 'Led Zeppelin'},\n        {'id': 2, 'played_date': 3, 'name': 'The Rolling Stones'},\n        {'id': 3, 'played_date': 2, 'name': 'Daft Punk'}\n    ]\n}\n</code></pre>\n\n<h3><code>data/searchresults.json</code></h3>\n\n<pre><code>{\n    'success': true,\n    'results': [\n        {'id': 1, 'name': 'Led Zeppelin'},\n        {'id': 2, 'name': 'The Rolling Stones'},\n        {'id': 3, 'name': 'Daft Punk'},\n        {'id': 4, 'name': 'John Mayer'},\n        {'id': 5, 'name': 'Pete Philly &amp;amp; Perquisite'},\n        {'id': 6, 'name': 'Black Star'},\n        {'id': 7, 'name': 'Macy Gray'}\n    ]\n}\n</code></pre>\n\n<h2>Models</h2>\n\n<p>Models in Ext JS 4 are very similar to Records which we had in Ext JS 3. One key difference is that you can now specify a proxy on your model, as well as validations and associations. The Song model for our application in Ext JS 4 would look like this.</p>\n\n<h3><code>app/model/Song.js</code></h3>\n\n<pre><code>Ext.define('Panda.model.Song', {\n    extend: 'Ext.data.Model',\n    fields: ['id', 'name', 'artist', 'album', 'played_date', 'station'],\n\n    proxy: {\n        type: 'ajax',\n        url: 'data/recentsongs.json',\n        reader: {\n            type: 'json',\n            root: 'results'\n        }\n    }\n});\n</code></pre>\n\n<p>As you can see, we have defined the proxy on our model. It is generally good practice to do this as it allows you to load and save instances of this model without needing a store. Also, when multiple stores use this same model, you don’t have to redefine your proxy on each one of them.</p>\n\n<p>Let’s go ahead and also define our Station model.</p>\n\n<h3><code>app/model/Station.js</code></h3>\n\n<pre><code>Ext.define('Panda.model.Station', {\n    extend: 'Ext.data.Model',\n    fields: ['id', 'name', 'played_date'],\n\n    proxy: {\n        type: 'ajax',\n        url: 'data/stations.json',\n        reader: {\n            type: 'json',\n            root: 'results'\n        }\n    }\n});\n</code></pre>\n\n<h2>Stores</h2>\n\n<p>In Ext JS 4, multiple stores can use the same data model, even if the stores will load their data from different sources. In our example, the Station model will be used by the SearchResults and the Stations store, both loading the data from a different location. One returns search results, the other returns the user’s favorite stations. To achieve this, one of our stores will need to override the proxy defined on the model.</p>\n\n<h3><code>app/store/SearchResults.js</code></h3>\n\n<pre><code>Ext.define('Panda.store.SearchResults', {\n    extend: 'Ext.data.Store',\n    requires: 'Panda.model.Station',\n    model: 'Panda.model.Station',\n\n    // Overriding the model's default proxy\n    proxy: {\n        type: 'ajax',\n        url: 'data/searchresults.json',\n        reader: {\n            type: 'json',\n            root: 'results'\n        }\n    }\n});\n</code></pre>\n\n<h3><code>app/store/Stations.js</code></h3>\n\n<pre><code>Ext.define('Panda.store.Stations', {\n    extend: 'Ext.data.Store',\n    requires: 'Panda.model.Station',\n    model: 'Panda.model.Station'\n});\n</code></pre>\n\n<p>In the SearchResults store definition, we have overridden the proxy defined on the Station model by providing a different proxy configuration. The store’s proxy is used when calling the store’s load method instead of the proxy defined on the model itself.</p>\n\n<p>Note that you could implement your server side to have one API for retrieving both search results and the user’s favorite stations in which case both stores could use the default proxy defined on the model, only passing different parameters to the request when loading the stores.</p>\n\n<p>Lastly, let’s create the RecentSongs store.</p>\n\n<h3><code>app/store/RecentSongs.js</code></h3>\n\n<pre><code>Ext.define('Panda.store.RecentSongs', {\n    extend: 'Ext.data.Store',\n    model: 'Panda.model.Song',\n\n    // Make sure to require your model if you are\n    // not using Ext JS 4.0.5\n    requires: 'Panda.model.Song'\n});\n</code></pre>\n\n<p>Note that in the current version of Ext JS, the 'model' property on a store doesn’t automatically create a dependency, which is why we have to specify requires in order to be able to dynamically load the model.</p>\n\n<p>Also, for convention, we always try to pluralize the store names, while keeping the model names singular.</p>\n\n<h2>Adding the stores and models to our application</h2>\n\n<p>Now that we have defined our models and stores, it’s time to add them to our application. Let’s revisit our Application.js file.</p>\n\n<h3><code>app/Application.js</code></h3>\n\n<pre><code>Ext.application({\n    ...\n    models: ['Station', 'Song'],\n    stores: ['Stations', 'RecentSongs', 'SearchResults']\n    ...\n});\n</code></pre>\n\n<p>Another advantage of using the new Ext JS 4 MVC package is that the Application will automatically load the stores and models defined in the stores and models configurations. Then, it will create an instance for each store loaded, giving it a storeId equal to its name. This allows us to use the name of the store whenever we bind it to a data component like we did in our views, e.g. store: 'SearchResults'.</p>\n\n<h2>Applying the glue</h2>\n\n<p>Now that we have our views, models and stores, it’s time to glue them together. You start by adding the views one by one to your viewport. This will make it easier to debug any wrong view configurations. Let’s go through the resulting viewport for the Panda app.</p>\n\n<pre><code>Ext.define('Panda.view.Viewport', {\n    extend: 'Ext.container.Viewport',\n</code></pre>\n\n<p>Your Viewport class will usually want to extend <a href=\"#!/api/Ext.container.Viewport\" rel=\"Ext.container.Viewport\" class=\"docClass\">Ext.container.Viewport</a>. This will cause your app to take up all the available space in your browser window.</p>\n\n<pre><code>    requires: [\n        'Panda.view.NewStation',\n        'Panda.view.SongControls',\n        'Panda.view.StationsList',\n        'Panda.view.RecentlyPlayedScroller',\n        'Panda.view.SongInfo'\n    ],\n</code></pre>\n\n<p>We set up all the view dependencies in our viewport. This will allow us to use their xtypes, previously configured in our views using the alias property.</p>\n\n<pre><code>    layout: 'fit',\n\n    initComponent: function() {\n        this.items = {\n            xtype: 'panel',\n            dockedItems: [{\n                dock: 'top',\n                xtype: 'toolbar',\n                height: 80,\n                items: [{\n                    xtype: 'newstation',\n                    width: 150\n                }, {\n                    xtype: 'songcontrols',\n                    height: 70,\n                    flex: 1\n                }, {\n                    xtype: 'component',\n                    html: 'Panda&lt;br&gt;Internet Radio'\n                }]\n            }],\n            layout: {\n                type: 'hbox',\n                align: 'stretch'\n            },\n            items: [{\n                width: 250,\n                xtype: 'panel',\n                layout: {\n                    type: 'vbox',\n                    align: 'stretch'\n                },\n                items: [{\n                    xtype: 'stationslist',\n                    flex: 1\n                }, {\n                    html: 'Ad',\n                    height: 250,\n                    xtype: 'panel'\n                }]\n            }, {\n                xtype: 'container',\n                flex: 1,\n                layout: {\n                    type: 'vbox',\n                    align: 'stretch'\n                },\n                items: [{\n                    xtype: 'recentlyplayedscroller',\n                    height: 250\n                }, {\n                    xtype: 'songinfo',\n                    flex: 1\n                }]\n            }]\n        };\n\n        this.callParent();\n    }\n});\n</code></pre>\n\n<p>Since Viewport extends Container, and Containers can’t have docked items (yet), we have added a Panel as the single item of our viewport. We make this panel the same size as our viewport by defining a layout of fit.</p>\n\n<p>In terms of architecture, one of the most important things to note here is the fact that we have not defined a layout-specific configuration in the actual views. By not defining properties like flex, width, height in the views, we can easily adjust the application’s overall layout in one single place, adding to the maintainability and flexibility of our architecture.</p>\n\n<h2>Application logic</h2>\n\n<p>In Ext JS 3, we often added our application’s logic to the views themselves using handlers on buttons, binding listeners to subcomponents, and overriding methods on the views when extending them. However, just like you shouldn’t inline CSS styles in your HTML markup, it’s preferable to separate the application’s logic from the view definitions. In Ext JS 4, we provide controlleres in the MVC package. They are responsible for listening to events fired by the views and other controllers, and for implementing application logic to act on those events. There are several benefits to this design.</p>\n\n<p>One benefit is that your application logic is not bound to instances of views which means we can destroy and instantiate our views, as needed, while the application logic continues processing other things, like synchronizing data.</p>\n\n<p>Additionally in Ext JS 3, you might have had many nested views, each adding layers of application logic. By moving the application logic to controllers, it is centralized, making it easier to maintain and change.</p>\n\n<p>Finally, the Controller base class provides you with lots of functionality, making it easier to implement your application logic.</p>\n\n<h2>Creating our Controllers</h2>\n\n<p>Now that we have the basic architecture for our UI, models and stores set up, it’s time to get in control of our application. We planned to have two controllers, Station and Song, so let’s create the definitions for them.</p>\n\n<h3><code>app/controller/Station.js</code></h3>\n\n<pre><code>Ext.define('Panda.controller.Station', {\n    extend: 'Ext.app.Controller',\n    init: function() {\n        ...\n    },\n    ...\n});\n</code></pre>\n\n<h3><code>app/controller/Song.js</code></h3>\n\n<pre><code>Ext.define('Panda.controller.Song', {\n    extend: 'Ext.app.Controller',\n    init: function() {\n        ...\n    },\n    ...\n});\n</code></pre>\n\n<p>When including the controllers in your application, the framework will automatically load the controller and call the init method on it. Inside the init method, you should set up listeners for your view and application events. In larger applications, you might want to load additional controllers at runtime. You can do this by using the getController method.</p>\n\n<pre><code>someAction: function() {\n    var controller = this.getController('AnotherController');\n\n    // Remember to call the init method manually\n    controller.init();\n}\n</code></pre>\n\n<p>When you load additional controllers at runtime, you have to remember to call the init method on the loaded controller manually.</p>\n\n<p>For the purposes of our example application, we’ll let the framework load and initialize our controllers by adding them to the controllers array in our application definition.</p>\n\n<h3><code>app/Application.js</code></h3>\n\n<pre><code>Ext.application({\n    ...\n    controllers: ['Station', 'Song']\n});\n</code></pre>\n\n<h2>Setting up listeners</h2>\n\n<p>Let’s start controlling some parts of our UI by using the control method inside of the controller’s init function.</p>\n\n<h3><code>app/controller/Station.js</code></h3>\n\n<pre><code>...\ninit: function() {\n    this.control({\n        'stationslist': {\n            selectionchange: this.onStationSelect\n        },\n        'newstation': {\n            select: this.onNewStationSelect\n        }\n    });\n}\n...\n</code></pre>\n\n<p>The control method is passed an object where the keys are component queries. In our example, the component queries are just using the xtypes of our views. However, using these component queries, you can target very specific parts of your UI. To learn more about advanced component queries, you can refer to the API docs.</p>\n\n<p>Each query is bound to a listener configuration. Inside each listener configuration, we want to listen for the key which is the event name. The events available are the ones provided by the component that is targeted by your query. In this case, we use the selectionchange event provided by Grid (from which our StationsList view extends) and the select event provided by ComboBox (from which our NewStation view extends). To find out which events are available for a particular component, you can look in the events section available for each component in the API docs.</p>\n\n<p><p><img src=\"guides/mvc_pt2/apidocs-events.png\" alt=\"\"></p></p>\n\n<p>The value in the listener configuration is the function that gets executed whenever that event fires. The scope of this function is always the controller itself.</p>\n\n<p>Let’s also set up some listeners in our Song controller.</p>\n\n<h3><code>app/controller/Song.js</code></h3>\n\n<pre><code>...\ninit: function() {\n    this.control({\n        'recentlyplayedscroller': {\n            selectionchange: this.onSongSelect\n        }\n    });\n\n    this.application.on({\n        stationstart: this.onStationStart,\n        scope: this\n    });\n}\n...\n</code></pre>\n\n<p>In addition to listening for the selectionchange event on our RecentlyPlayedScroller view, we also set up a listener for an application event here. We do this by using the on method on the application instance. Each controller has access to the application instance using the this.application reference.</p>\n\n<p>Application events are extremely useful for events that have many controllers. Instead of listening for the same view event in each of these controllers, only one controller listens for the view event and fires an application-wide event that the others can listen for. This also allows controllers to communicate with one another without knowing about or depending on each other’s existence.</p>\n\n<p>Our Song controller is interested in a new station being started because it needs to update the song scroller and song info whenever this happens.</p>\n\n<p>Let’s take a look at how the Station controller, which will be the one responsible for firing this stationstart application event, actually does this.</p>\n\n<h3><code>app/controller/Station.js</code></h3>\n\n<pre><code>...\nonStationSelect: function(selModel, selection) {\n    this.application.fireEvent('stationstart', selection[0]);\n}\n...\n</code></pre>\n\n<p>We simply get the single selected item provided by the selectionchange event and pass it as the single argument when firing the stationstart event.</p>\n\n<h2>Conclusion</h2>\n\n<p>In this article, we have looked at the basic techniques of architecting your application. Of course, there is a lot to it, and in the next part of this series we will take a look at some more advanced controller techniques and continue wiring up our Panda app by implementing our controller actions and adding some more details to our views.</p>\n","title":"App Architecture Part 2"});