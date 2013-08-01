![alt text](https://github.com/markash/caratt/raw/master/logo.png "Carat")

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


### JSF


### Shiro ###


### JOOQ ###

I have used JDBI and JPA (EclipseLink) to perform the role of persistence manager before and I wanted to experiment with the concept of the Java code using the SQL DSL directly in the code because I actually enjoy writing SQL and agree with the JOOQ author that SQL is the perfect DSL for a SQL data store.

### Jetty ###

### Arquillian ###

[Wikipedia definition]: http://en.wikipedia.org/wiki/Carat_(mass)