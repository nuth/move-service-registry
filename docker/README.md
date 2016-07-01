# Kjøre Service Registry i en isolert Docker-container

## Bygging og kjøring

Samme fremgangsmåte som for integrasjonspunktet (forutsetter at Dockerfile og security.zip er kopiert til rot):

```shell
$ docker build --no-cache -t difi/serviceregistry .
...
$ docker create --name difi_sr -p 9099:9099 difi/serviceregistry
...
$ docker start difi_sr
...
```

