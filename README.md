# Service Registry

The service registry component (SR) is a component designed to run centralized, and has the follwing main features

* It Creates a single network address participants need to access. This simplifies firewall configuration.
* It Proxies certificate lookups. This enables one organisation to manage messaging others. End to end encryption
* It enables organisation number swapping. When a lookup on an organisation is done, the organisaiton number may be swapped out for some services

## Example request

http://localhost:8080/organization/986186999

## Example response

# The response always has an Information record. The information record says what channel the organization wants messages sent over (POST VIRKSOMHET, EDU etc)
# The response has 1..N ServiceRecords. Each Service record represents one channel the organisation can receive messages over. For each ServiceRecord there is a certificate, EndPoint and organzation number.
# The sender can not use the receivers organization number blindly. The sender must always use the organisation number in the relevant ServiceRecord. This is because the message might be   

```json
{
  "serviceRecords": [
    {
      "serviceRecord": {
        "serviceIdentifier": "EDU",
        "organisationNumber": "986186999",
        "x509Certificate": "-----BEGIN CERTIFICATE-----\nMIID/DCCAuSgAwIBAgIEYs5oXjANBgkqhkiG9w0BAQsFADBeMRIwEAYDVQQKEwlE\naWZpIHRlc3QxEjAQBgNVBAUTCTk5MTgyNTgyNzE0MDIGA1UEAxMrRElGSSB0ZXN0\nIHZpcmtzb21oZXRzc2VydGlmaWF0IGludGVybWVkaWF0ZTAeFw0xNTExMTYwODIy\nNDlaFw0xNzEyMTYwODIyNDlaMD0xEjAQBgNVBAUTCTk4NjE4Njk5OTEnMCUGA1UE\nAxMeRElGSSB0ZXN0IHZpcmtzb21oZXRzc2VydGlmaWF0MIIBIjANBgkqhkiG9w0B\nAQEFAAOCAQ8AMIIBCgKCAQEAvsKx0wO6pkRrClupprDxEx3imfXupLukcJCYE6Pz\n6915ZSDvURb+c+k+7p14hT193NiWcIOxvsldu43+hCQy5m4NEasRWMJTrTvuGt+q\nlykwCOW3955wIYIaI9pzGmH1qyGBqJJYSU+t/T8GbQSUweyZBxYdwxOotZKS3IU0\nnczP7gmu2/By8hFPdRf+b/ELsMhWHBwY70bpljYrtZG4lPdmpfC6TFljN/2dKGqX\n1QaszeDOLnNaiZNFRBbSaV8mrMktoeH4Jx6SixfppDz6Lioh8cxdaMBxOGlGd+vf\nK1MvFNdFfgSfPFqG9Y093KC4PrIeZuTqwmRDYsXY68HaTwIDAQABo4HiMIHfMIGL\nBgNVHSMEgYMwgYCAFCeuypqN1OjDIoi4bYBAcNfT83GzoWKkYDBeMRIwEAYDVQQK\nEwlEaWZpIHRlc3QxEjAQBgNVBAUTCTk5MTgyNTgyNzE0MDIGA1UEAxMrRElGSSB0\nZXN0IHZpcmtzb21oZXRzc2VydGlmaWF0IGludGVybWVkaWF0ZYIEJoRZSzAdBgNV\nHQ4EFgQU5yU6DYMxrB8EKC/lGoaHAkhaTcYwCQYDVR0TBAIwADAVBgNVHSAEDjAM\nMAoGCGCEQgEBAQFkMA4GA1UdDwEB/wQEAwIEsDANBgkqhkiG9w0BAQsFAAOCAQEA\nQq2NAMB1Q68uGpZpOp5br2mMmz3FhwAo/uTbJG0IYHRQhMGmufyQrU2SeEIgcLBd\ne8jyA3G5hcuXWNPHslpBP9TfbEPwViTS49PJwmlZ5J7xYI7JX1OjzjLBX2upnQK4\nY1gAMMWYJMh3eiRdRuRenBxFXH57JTNr5/viH81mcJnICjEaVzFyAzbG6PqU4mIX\nz1RxESkGQJkbXlMN2mKL9xSrwNl2LxRYjogOD27kW1/+jfazDWN9aaA7Txkbcsy+\nAHXSybQT/gWiYEdS4ZRrS9KukloEeDP9uytqnsa67O8u9caKwVeKxlLsmK9YvUIV\nINmlpJtvJQ+SCeuvfsxDsw==\n-----END CERTIFICATE-----\n",
        "endPointURL": "https://www.altinn.no"
      },
      "_links": {
        "setprimary": {
          "href": "http://localhost:8080/organization/primary?orgnr=986186999&serviceidentifier=EDU"
        }
      }
    },
    {
      "serviceRecord": {
        "serviceIdentifier": "POST_VIRKSOMHET",
        "organisationNumber": "986186999",
        "x509Certificate": "-----BEGIN CERTIFICATE-----\nMIID/DCCAuSgAwIBAgIEYs5oXjANBgkqhkiG9w0BAQsFADBeMRIwEAYDVQQKEwlE\naWZpIHRlc3QxEjAQBgNVBAUTCTk5MTgyNTgyNzE0MDIGA1UEAxMrRElGSSB0ZXN0\nIHZpcmtzb21oZXRzc2VydGlmaWF0IGludGVybWVkaWF0ZTAeFw0xNTExMTYwODIy\nNDlaFw0xNzEyMTYwODIyNDlaMD0xEjAQBgNVBAUTCTk4NjE4Njk5OTEnMCUGA1UE\nAxMeRElGSSB0ZXN0IHZpcmtzb21oZXRzc2VydGlmaWF0MIIBIjANBgkqhkiG9w0B\nAQEFAAOCAQ8AMIIBCgKCAQEAvsKx0wO6pkRrClupprDxEx3imfXupLukcJCYE6Pz\n6915ZSDvURb+c+k+7p14hT193NiWcIOxvsldu43+hCQy5m4NEasRWMJTrTvuGt+q\nlykwCOW3955wIYIaI9pzGmH1qyGBqJJYSU+t/T8GbQSUweyZBxYdwxOotZKS3IU0\nnczP7gmu2/By8hFPdRf+b/ELsMhWHBwY70bpljYrtZG4lPdmpfC6TFljN/2dKGqX\n1QaszeDOLnNaiZNFRBbSaV8mrMktoeH4Jx6SixfppDz6Lioh8cxdaMBxOGlGd+vf\nK1MvFNdFfgSfPFqG9Y093KC4PrIeZuTqwmRDYsXY68HaTwIDAQABo4HiMIHfMIGL\nBgNVHSMEgYMwgYCAFCeuypqN1OjDIoi4bYBAcNfT83GzoWKkYDBeMRIwEAYDVQQK\nEwlEaWZpIHRlc3QxEjAQBgNVBAUTCTk5MTgyNTgyNzE0MDIGA1UEAxMrRElGSSB0\nZXN0IHZpcmtzb21oZXRzc2VydGlmaWF0IGludGVybWVkaWF0ZYIEJoRZSzAdBgNV\nHQ4EFgQU5yU6DYMxrB8EKC/lGoaHAkhaTcYwCQYDVR0TBAIwADAVBgNVHSAEDjAM\nMAoGCGCEQgEBAQFkMA4GA1UdDwEB/wQEAwIEsDANBgkqhkiG9w0BAQsFAAOCAQEA\nQq2NAMB1Q68uGpZpOp5br2mMmz3FhwAo/uTbJG0IYHRQhMGmufyQrU2SeEIgcLBd\ne8jyA3G5hcuXWNPHslpBP9TfbEPwViTS49PJwmlZ5J7xYI7JX1OjzjLBX2upnQK4\nY1gAMMWYJMh3eiRdRuRenBxFXH57JTNr5/viH81mcJnICjEaVzFyAzbG6PqU4mIX\nz1RxESkGQJkbXlMN2mKL9xSrwNl2LxRYjogOD27kW1/+jfazDWN9aaA7Txkbcsy+\nAHXSybQT/gWiYEdS4ZRrS9KukloEeDP9uytqnsa67O8u9caKwVeKxlLsmK9YvUIV\nINmlpJtvJQ+SCeuvfsxDsw==\n-----END CERTIFICATE-----\n",
        "endPointURL": null
      },
      "_links": {
        "setprimary": {
          "href": "http://localhost:8080/organization/primary?orgnr=986186999&serviceidentifier=POST_VIRKSOMHET"
        }
      }
    }
  ],
  "infoRecord": {
    "primaryServiceIdentifier": "POST_VIRKSOMHET",
    "organisationNumber": "986186999"
  }
} 
```


