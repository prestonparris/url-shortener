### dependencies
`gradle`
`java 8`

### run application

`./gradlew bootRun`

then visit 

`http://localhost:8080`

in a browser

### run tests
`./gradlew `

### system design explanation
I decided to go with a highly scalable solution based on pre-computing all of the possible 6 character tinyUrl strings. This means generating initially and storing 68.7B (unique keys). Currently only generating a small subset for testing. These keys would be stored in a distributed key value store such as cassandra allowing near constant read and write times without encountering hash key collisions. There would need to be a service to potentially expire old keys and add them back to the pool of available keys.

The KeyGenerator script is intended to be ran initially to populate the unused keys table.

There is a key service which is just responsible for providing a new unique key to the url service. The url service stores the mappings between smallUrl to largeUrl and largeUrl to smallUrl.  

