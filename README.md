# Service Registry

The service registry component (SR) is a component designed to run centralized, and has the follwing main features

* It Creates a single network address participants need to access. This simplifies firewall configuration.
* It Proxies certificate lookups. This enables one organisation to manage messaging others. End to end encryption
* It enables organisation number swapping. When a lookup on an organisation is done, the organisaiton number may be swapped out for some services

## Sample request

Assume the server is running on localhost on port 8080, the following GET requst

http://localhost:8080/organization/972417831

Will give a response like this



