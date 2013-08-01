![alt text](https://github.com/markash/carat/raw/master/logo.png "Carat")

## Exordium

> Definition : A unit of weight for precious stones and pearls, now equivalent to 200 milligrams. [Wikipedia][Wikipedia definition]

## Narratio

Carat is a seed web project used as a Maven WAR overlay so that subprojects can re-use common configuration in this projects.
The style of web project that Carat caters for is the run-of-the-mill-enterprisie-app that has
* user management
* batches
* some other enty stuff

Carat was started with the release of Glassfish 4.0 and JEE 7.0 but surprisingly it does not use many of the JEE 7.0
components since I wanted to experiment with other frameworks like JOOQ instead of JPA for example. Also another
requirement is that I wanted the target application to run in Jetty so that developer productivity is at a maximum
without the usual code-build-deploy-debug cycle JEE developers are hampered by. At first this requirement was
satisfied but I must confess that the Maven WAR overlay (i.e. mvn jetty:run-war) instead of (i.e. mvn jetty:run) does
seem disable the hot reloading unless of course there is a work-around like performing a re-package (i.e. mvn package) but
more experimentation is required.

## Divisio


#### JSF

This JEE 7.0 component together with CDI is only ones to remain from the original POC project since I enjoy JSF
development and in the carat-web library project has started to experiment with Bootstrap - RichFaces CDK - JSF. The objective
here is to choose a web framework and understand it's strengths and weaknesses which all frameworks have.

#### Shiro

Apache Shiro is currently the Security Framework but the migration to Spring Security might happen soon since Shiro does
not seem to support OpenID.

#### JOOQ

I have used JDBI and JPA (EclipseLink) to perform the role of persistence manager before and I wanted to experiment
with the concept of the Java code using the SQL DSL directly in the code because I actually enjoy writing SQL and
agree with the JOOQ author that SQL is the perfect DSL for a SQL data store.

#### ModelMapper

ModelMapper dependency is in the project but not being used because the integration with JOOQ did not seemt to work as
explained on the relevant websites so the object graph construction is manual currently.

#### Jetty

Jetty 9 supports many of the features, like WebSockets, that I wanted to experiment with so it was a natural choice. I
have tried to get Tomcat 8 and it's associated Maven plugin to work but have come up short for the time being. Another
big plus was the Codehale Metrics had plug-ins for Jetty to retrieve metrics so it was the initial choice.


#### Arquillian

Testing...testing..and some more testing. Although there are not many tests within the project yet these will be exapnded over time.
In my opinion Arquillian is the de facto standard of in-container testing to determine how a piece of code will actually behave
when in production. Mocks have a place but does not substitute this piece of software.

#### Spock Framework

This is a new addition to the project and is not quite working correctly because of dependency issues with the Spock libraries but persistence is gold.

#### Webjars

Mmmmm....excellent idea!!!

#### JBatch

The evaluation of JBatch is underway and the initial feeling is that it is not as production ready as Spring Batch since
there was a lot of debugging required to get it to work with PostgreSQL and in Jetty.

#### CDI

CDI is in because it is part of the JEE 7.0 spec but it has been a rocky start to get it to work correctly with JSF (but that is always the case with JSF / CDI).

[Wikipedia definition]: http://en.wikipedia.org/wiki/Carat_(mass)