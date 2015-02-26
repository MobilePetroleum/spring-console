Spring Console
===
Spring Console is a Spring management & productivity tool.<br/>
Spring Console can call any method of any bean on any number of spring context.

Works with Spring 2.0+ & Java 1.5+.

'''
<dependency>
	<groupId>com.mobilepetroleum</groupId>
	<artifactId>spring-console</artifactId>
	<version>1.2.3</version>
</dependency>
'''

# Use Spring Console
## Server side
`<bean name="springConsole" class="com.mobilepetroleum.SpringConsole"/>`

## Client side
To execute server side code run: <br/>
sco e fileToExecute.json<br/>
( <b>S</b>pring <b>CO</b>nsole <b>E</b>xecute fileToExecute.json)

Sample fileToExecute.json:
```
{
    "remotes": [
        { "name": "local", "ip" : "localhost" },
    ],

    "invocations" : [
        { "beanName": "returnerBean", "method": "returnBigChar" },
        {
            "beanName": "sampleBean",
            "method": "callWithCustomObject",
            "args": ["1", "123", { "string": "asd", "anInt": 123 }]
        }
    ]
}
```
