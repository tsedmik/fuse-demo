# fuse-demo
New Fuse project development walkthrough -- Red Hat Fuse Apicurito -> Red Hat Developer Studio -> OpenShift

Please see my screencast on youtube:

[![IMAGE ALT TEXT HERE](https://img.youtube.com/vi/p6LeZoD-Jj0/0.jpg)](https://www.youtube.com/watch?v=p6LeZoD-Jj0)

The example project created in the video is available in this repository see `./addressbook-project`

You may notice a little trick in the video. The trick consists of developing the application in Red Hat Developer Studio with the following steps:

1. create a simple POJO class for an Entry - see `io.example.openapi.Entry`
1. implement the business logic for our application (manipulation with data) - see `io.example.openapi.EntryService`
1. modify Camel Context XML file `src/main/resources/spring/camel-context.xml`:
   1. add a bean definition to be able to access the business logic
   
   ```
   <beans ...>
     ...
     <bean class="io.example.openapi.EntryService" id="entryService" scope="singleton"/>
     ...
     <camelContext ...>
   </beans>
   ```

   2. change routes endpoints to point to appropriate bean methods

   ```
   ...
   <route id="route-a3b3a495-7e70-4f81-a109-b01b8f5db72a">
     <from id="from-e2c82e5b-e320-45a6-8934-8f802dcf3f7e" uri="direct:getEntries"/>
     <bean id="_bean1" method="getAllEntries" ref="entryService"/>
   </route>
   ...
   ```

   3. add `type` attribute to _PUT_ and _POST_ methods

   ```
   ...
   <post id="createEntry" uri="/entries" type="io.example.openapi.Entry">
     <description>Creates a new instance of a `Entry`.</description>
     <param description="A new `Entry` to be created." name="body" required="true" type="body"/>
     <to uri="direct:createEntry"/>
   </post>
   <put id="updateEntry" uri="/entries/{entryId}" type="io.example.openapi.Entry">
     <description>Updates an existing `Entry`.</description>
     <param description="Updated `Entry` information." name="body" required="true" type="body"/>
     <to uri="direct:updateEntry"/>
   </put>
   ...
   ```