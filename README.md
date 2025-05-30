# ms-claim-to-pdf

A Java Springboot service within Access to Work (AtW) that will take in json, convert to html and then convert the html to a pdf. The pdf is encrypted and uploaded to a s3 bucket. 

## Service Endpoints

### POST /generate/claim-form for support worker
```json5
{
    "id": 49,
    "createdDate": "2022-01-30T13:31:05.775",
    "lastModifiedDate": "2022-01-31T14:27:32.02",
    "claimStatus": "AWAITING_DRS_UPLOAD",
    "nino": "RN000013A",
    "claimType": "SUPPORT_WORKER",
    "cost": 2000.0,
    "hasContributions": true,
    "atwNumber" : "ATW1234567",
    "claimant": {
      "forename": "Odin",
      "surname": "Surtsson",
      "dateOfBirth": "1930-11-12",
      "emailAddress": "Odin.Surtsson@gmail.com", //optional
      "homeNumber": "07700900630", //optional
      "company": "Apple Inc",
      "address": {
        "address1": "1 The Street",
        "address2": "Village Name", // Optional
        "address3": "Town",
        "address4": "County", // Optional
        "postcode": "NE26 4RS"
      }
    },   
    "evidence": [
        {
            "fileId": "09672038-7155-4cb9-8b2e-56eda1fd6b53/7e67a9c5-f7c7-4565-a2ab-6c36530e0710",
            "fileName": "6b99f480c27e246fa5dd0453cd4fba29.pdf"
        },
        {
            "fileId": "b9c2ea02-f424-4cd3-bdc1-0ab1c26706fe/cfda6946-7bb5-4886-8b27-beaccbd8e834",
            "fileName": "Technical Architect.docx"
        }
    ],
    "payee": { 
        "newPayee": true, 
        "details": {
            "fullName": "INeed Paying",
            "emailAddress": "payment@now.com"// Optional if newPayee is set to false
        },
        "address": { // if newPayee is set to false, address is not required
            "address1": "THE COTTAGE",
            "address2": "ST. MARYS ISLAND", // Optional
            "address3": "WHITLEY BAY",
            "address4": null, // Optional
            "postcode": "NE26 4RS"
        },
        "bankDetails": {// if newPayee is set to false, bankDetails accountNumber is the only one required
            "accountHolderName": "Ineed Paying",
            "sortCode": "000004",
            "accountNumber": "12345677", // required
            "rollNumber": null
        }
    },
    "declarationVersion": 2.1,
    "nameOfSupport": "person 1", //Optional
    "claim": {
        "0": {
            "monthYear": {
                "mm": "04",
                "yyyy": "2020"
            },
            "claim": [
                {
                    "dayOfSupport": "01",
                    "timeOfSupport": {
                        "hoursOfSupport": 2,
                        "minutesOfSupport": 15
                    }                   
                },
                {
                    "dayOfSupport": "02",
                    "timeOfSupport": {
                        "hoursOfSupport": 3,
                        "minutesOfSupport": 0
                    }
                }
            ]
        },
        "1": {
            "monthYear": {
                "mm": "05",
                "yyyy": "2020"
            },
            "claim": [
                {
                    "dayOfSupport": "12",
                    "timeOfSupport": {
                        "hoursOfSupport": 12,
                        "minutesOfSupport": 15
                    }
                },
                {
                    "dayOfSupport": "14",
                    "timeOfSupport": {
                        "hoursOfSupport": 0,
                        "minutesOfSupport": 15
                    }
                }
            ]
        }
    },
    "workplaceContact": {
        "emailAddress": "count@signer.com",
        "fullName": "Count Signer",
        "organisation": "company2",
        "jobTitle": "boss2",
        "updatedOn": "2022-01-31T14:27:32.02",
        "declarationVersion": 3.1,
        "address": {
            "address1": "THE COTTAGE 3",
            "address2": "ST. MARYS ISLAND", // Optional
            "address3": "WHITLEY BAY",
            "postcode": "NE26 4RS"
        }
    }
}
```

#### Response

200 - Ok

```json5
{
    "fileId": "claim-forms/SUPPORT_WORKER/49/24d83c22-3243-45f1-884b-a10916ad21f5"
}
```

### POST /generate/claim-form for Specialist Equipment
```json5
{
    "id": 6,
    "createdDate": "2022-02-17T15:35:14.432",
    "lastModifiedDate": "2022-02-17T15:35:14.432",
    "claimStatus": "AWAITING_DRS_UPLOAD",
    "nino": "RN000013A",
    "claimType": "EQUIPMENT_OR_ADAPTATION",
    "cost": 2211.0,
    "hasContributions": true,
    "atwNumber": "ATW1234567",
    "claimant": {
        "forename": "Odin",
        "surname": "Surtsson",
        "dateOfBirth": "1930-11-12",
        "emailAddress": "Odin.Surtsson@gmail.com", //optional
        "homeNumber": "07700900630", //optional
        "company": "Apple Inc",
        "address": {
          "address1": "1 The Street",
          "address2": "Village Name", // Optional
          "address3": "Town",
          "address4": "County", // Optional
          "postcode": "NE26 4RS"
        }
      },
    "payee": {
       "newPayee": true, //for EA newPayee must be true
       "details": {
          "fullName": "INeed Paying",
          "emailAddress": "payment@now.com"
       },
       "address": {
          "address1": "THE COTTAGE",
          "address2": "ST. MARYS ISLAND", // Optional
          "address3": "WHITLEY BAY",
          "address4": "WHITLEY BAY", // Optional
          "postcode": "NE26 4RS"
       },
       "bankDetails": {
          "accountHolderName": "Ineed Paying",
          "sortCode": "000004",
          "accountNumber": "12345677",  //required
          "rollNumber": "12345677" //optional
       }
    },
    "declarationVersion": 2.1,
    "evidence": [
        {
            "fileId": "633ce73b-1414-433e-8a08-72449a0244fc/144b2aca-996d-4c27-bdf2-1e9b418874d3",
            "fileName": "6b99f480c27e246fa5dd0453cd4fba29.pdf"
        }
    ],
    "claim": {
        "0": [
          {
            "description": "Item 2",
            "dateOfPurchase": {
              "dd": "22",
              "mm": "11",
              "yyyy": "2020"
            }
          }
        ],
        "1": [
          {
            "description": "Item 2",
            "dateOfPurchase": {
              "dd": "18",
              "mm": "12",
              "yyyy": "2020"
            }
          }
        ]
    }
}
```

#### Response

200 - Ok

```json5
{
"fileId": "claim-forms/EQUIPMENT_OR_ADAPTATION/6/c5de70cc-66c8-4012-be55-be1e2f42f700"
}
```



### POST /generate/claim-form for Adaptation to Vehicle
```json5
{
    "id": 6,
    "createdDate": "2022-02-17T15:35:14.432",
    "lastModifiedDate": "2022-02-17T15:35:14.432",
    "claimStatus": "AWAITING_DRS_UPLOAD",
    "nino": "RN000013A",
    "claimType": "ADAPTATION_TO_VEHICLE",
    "cost": 2211.0,
    "hasContributions": true,
    "atwNumber": "ATW1234567",
      "claimant": {
        "forename": "Odin",
        "surname": "Surtsson",
        "dateOfBirth": "1930-11-12",
        "emailAddress": "Odin.Surtsson@gmail.com", //optional
        "homeNumber": "07700900630", //optional
        "mobileNumber": "01000000000", //optional
        "company": "Apple Inc",
        "address": {
          "address1": "THE COTTAGE",
          "address2": "ST. MARYS ISLAND", // Optional
          "address3": "WHITLEY BAY",
          "address4": "WHITLEY BAY", // Optional
          "postcode": "NE26 4RS"
        }
      },
   "payee": {
        "newPayee": true, //for AV newPayee must be true
        "details": {
            "fullName": "Ineed Paying",
            "emailAddress": "payment@now.com"
        },
        "address": {
            "address1": "THE COTTAGE",
            "address2": "ST. MARYS ISLAND", // Optional
            "address3": "WHITLEY BAY",
            "address4": "WHITLEY BAY", // Optional
            "postcode": "NE26 4RS"
        },
        "bankDetails": {
            "accountHolderName": "Ineed Paying",
            "sortCode": "000004",
            "accountNumber": "12345677",
            "rollNumber": "12345677" //optional
        }
    },
    "declarationVersion": 2.1,
    "evidence": [
        {
            "fileId": "633ce73b-1414-433e-8a08-72449a0244fc/144b2aca-996d-4c27-bdf2-1e9b418874d3",
            "fileName": "6b99f480c27e246fa5dd0453cd4fba29.pdf"
        }
      ],
      "claim": {
        "0": {
          "claimDescription": [
            {
              "description": "Item 1",
              "dateOfInvoice": {
                "dd": "22",
                "mm": "11",
                "yyyy": "2020"
              }
            }
          ]
        },
        "1": {
          "claimDescription": [
            {
              "description": "Item 2",
              "dateOfInvoice": {
                "dd": "2",
                "mm": "11",
                "yyyy": "2020"
              }
            }
          ]
        }
      }
}
```

#### Response

200 - Ok

```json5
{
"fileId": "claim-forms/ADAPTATION_TO_VEHICLE/6/c5de70cc-66c8-4012-be55-be1e2f42f700"
}
```

### POST /generate/claim-form for Travel to Work

```json5
{
    "id": 9,
    "createdDate": "2022-02-18T17:55:24.669",
    "lastModifiedDate": "2022-02-19T17:55:50.841",
    "claimStatus": "AWAITING_DRS_UPLOAD",
    "nino": "RN000013A",
    "claimType": "TRAVEL_TO_WORK",
    "travelDetails": {
       "howDidYouTravelForWork": "lift", // Values: lift or taxi
      "journeysOrMileage": "mileage"// Optional. Will only be show if above is lift. Values: mileage or Journeys
    },
    "cost": 121, // If TW and howDidYouTravelForWork == lift then it will not be presented
    "hasContributions": true,
    "atwNumber": "ATW1234567",
    "claimant": {
        "forename": "Odin",
        "surname": "Surtsson",
        "dateOfBirth": "1930-11-22",
        "emailAddress": "Odin.Surtsson@gmail.com", //optional
        "homeNumber": "01277777777", //optional
        "mobileNumber": "07700900630", //optional
        "company": "Company 1",
        "address": {
          "address1": "1 The Street",
          "address2": "Village Name", // Optional
          "address3": "Town",
          "address4": "County", // Optional
          "postcode": "NE26 4RS"
        }
    },
    "payee": { 
      "newPayee": true,
      "details": { // details must always be present
        "fullName": "INeed Paying",
        "emailAddress": "payment@now.com"// Optional if newPayee is set to false
      },
      "address": {// if newPayee is set to false, address is not required
         "address1": "THE COTTAGE",
         "address2": "ST. MARYS ISLAND", // Optional
         "address3": "WHITLEY BAY",
         "address4": "WHITLEY BAY", // Optional
         "postcode": "NE26 4RS"
      },
      "bankDetails": {// if newPayee is set to false, bankDetails accountNumber is the only one required
         "accountHolderName": "Ineed Paying",
         "sortCode": "000004",
         "accountNumber": "12345677", //required
         "rollNumber": "12345677" // optional
      }
    },
    "declarationVersion": 2.1,
    "workplaceContact": {
      "emailAddress": "Count@sign.com",
      "fullName": "Count Signer",
      "organisation": "company2",
      "jobTitle": "boss2",
      "address": {
        "address1": "THE COTTAGE",
        "address2": "ST. MARYS ISLAND",
        "address3": "WHITLEY BAY",
        "address4": "WHITLEY BAY",
        "postcode": "NE26 4RS"
      },
      "updatedOn": "2022-02-19T17:55:50.841",
      "employmentStatus": "employed",
      "declarationVersion": 3.1
    },
    "evidence": [
        {
          "fileId": "583d32fb-4b1d-418b-a25b-d1a47feb095f/4a5d1bdf-7b60-402d-93bf-de3e7a369044",
          "fileName": "6b99f480c27e246fa5dd0453cd4fba29.pdf"
        }
    ],
    "claim": {
        "0": {
          "monthYear": {
            "mm": "04",
            "yyyy": "2020"
          },
          "claim": [
            {
              "dayOfTravel": "12",
              "totalTravel": "13" // Both fields are required. No optional fields as this is a TW specific field
            }
          ]
        },
        "1": {
          "monthYear": {
            "mm": "05",
            "yyyy": "2020"
          },
          "claim": [
            {
              "dayOfTravel": "12",
              "totalTravel": "13"
            }
          ]
        }
    }
}
```

#### Response

200 - Ok

```json5
{
    "fileId": "claim-forms/TRAVEL_TO_WORK/9/3f72dbc7-a6cd-4615-b8fe-52fd28906aa0"
}
```

### POST /generate/claim-form for Travel in Work

```json5
{
  "id": 9,
  "createdDate": "2024-02-27T11:03:11.594",
  "lastModifiedDate": "2024-02-27T13:46:00.123",
  "claimStatus": "AWAITING_DRS_UPLOAD",
  "nino": "RN000013A",
  "claimType": "TRAVEL_IN_WORK",
  "cost": 2211.0,
  "hasContributions": true,
  "atwNumber": "ATW1234567",
  "claimant": {
    "forename": "Odin",
    "surname": "Surtsson",
    "dateOfBirth": "1930-11-12",
    "emailAddress": "Odin.Surtsson@gmail.com",  //optional
    "homeNumber": "01233665544",  //optional
    "mobileNumber": "07700900630", //optional
    "company": "Company 1",
    "address": {
      "address1": "1 The Street",
      "address2": "Village Name", // Optional
      "address3": "Town",
      "address4": "County", // Optional
      "postcode": "NE26 4RS"
    }
  },
  "payee": { 
    "newPayee": true,
    "details": {
      "fullName": "INeed Paying",
      "emailAddress": "name@name.com"// Optional if newPayee is set to false
    },
    "address": {// if newPayee is set to false, address is not required
      "address1": "THE COTTAGE",
      "address2": "ST. MARYS ISLAND",  //optional
      "address3": "WHITLEY BAY",
      "address4": "WHITLEY BAY", //optional
      "postcode": "NE26 4RS"
    },
    "bankDetails": { // if newPayee is set to false, bankDetails accountNumber is the only one required
      "accountHolderName": "INeed Paying",
      "sortCode": "000004",
      "accountNumber": "12345677", // required
      "rollNumber": "12345677" //optional
    }
  },
  "previousClaimId": 3,
  "documentBatchRequestId": null,
  "workplaceContact": {
    "emailAddress": "Count@sign.com",
    "fullName": "Count Signer",
    "organisation": "bq bridl",
    "jobTitle": "boss2",
    "address": {
      "address1": "b q bridl",
      "address2": "ST. MARYS ISLAND", // Optional
      "address3": "WHITLEY BAY",
      "address4": "WHITLEY BAY", // Optional
      "postcode": "NE26 4RS"
    },
    "updatedOn": "2024-02-27T13:46:00.116",
    "employmentStatus": "employed",
    "declarationVersion": 3.0
  },
  "evidence": [
    {
      "fileId": "633ce73b-1414-433e-8a08-72449a0244fc/144b2aca-996d-4c27-bdf2-1e9b418874d3",
      "fileName": "6b99f480c27e246fa5dd0453cd4fba29.pdf"
    }
  ],
  "totalMileage": 2,
  "declarationVersion": 2.1,
  "claim": {
    "0": {
      "monthYear": {
        "mm": "04",
        "yyyy": "2020"
      },
      "claim": [
        {
          "dayOfTravel": 12,
          "startPostcode": "xx11 1xx",
          "endPostcode": "xx11 2xx",
          "costOfTravel": 13.0 // All fields are required. No optional fields as this is a TIW specific field
        },
        {
          "dayOfTravel": 13,
          "startPostcode": "xx11 1xx",
          "endPostcode": "xx11 2xx",
          "costOfTravel": 14.0
        },
        {
          "dayOfTravel": 14,
          "startPostcode": "xx11 1xx",
          "endPostcode": "xx11 2xx",
          "costOfTravel": 15.0
        }
      ]
    },
    "1": {
      "monthYear": {
        "mm": "05",
        "yyyy": "2020"
      },
      "claim": [
        {
          "dayOfTravel": 13,
          "startPostcode": "xx11 1xx",
          "endPostcode": "xx11 2xx",
          "costOfTravel": 14.0
        },
        {
          "dayOfTravel": 14,
          "startPostcode": "xx11 1xx",
          "endPostcode": "xx11 2xx",
          "costOfTravel": 15.0
        }
      ]
    }
  }
}
```

#### Response

200 - Ok

```json5
{
    "fileId": "claim-forms/TRAVEL_IN_WORK/9/3f72dbc7-a6cd-4615-b8fe-52fd28906aa0"
}
```





### POST /generate/update-contact-details

```json5
{
    "contactInformationStatus": "AWAITING_DRS_UPLOAD",
    "createdDate": "2022-02-28T15:01:32.357",
    "accessToWorkNumber": "ATW12006521",
    "declarationVersion": 2.3,
    "currentContactInformation": {
        "forename": "Martha",
        "surname": "Ledgrave",
        "dateOfBirth": "1930-11-12",
        "emailAddress": "martha.ledgrave@email.com", //optional
        "homeNumber": "07627847834", //optional
        "address": {
            "address1": "THE COTTAGE",
            "address2": "ST. MARYS ISLAND", // Optional
            "address3": "WHITLEY BAY",
            "address4": null, // Optional
            "postcode": "NE26 4RS"
        }
    },
    "newContactInformation": {
        "forename": "Martha",
        "surname": "Ledgrave",
        "dateOfBirth": "1930-11-12",
        "emailAddress": "martha.ledgrave@hotmail.com", //optional
        "homeNumber": "07627847834", //optional
        "address": {
            "address1": "15 Redburry Grove",
            "address2": "Bramhope", // Optional
            "address3": "Leeds",
            "address4": "West Yorkshire", // Optional
            "postcode": "LS6 7RU"
        }
    }
}
```

#### Response

200 - Ok

```json5
{
    "fileId": "claim-forms/contact-information/ATW12006521/988e17f1-332f-44d3-b4af-f0222521059d"
}
```

### POST /generate/create-payee

```json5
{
  "contactInformationStatus": "/generate/update-contact-details",
  "createdDate": "2022-02-28T15:01:32.357",
  "accessToWorkNumber": "ATW12006521",
  "declarationVersion": 2.3,
  "currentContactInformation": {
    "forename": "Martha",
    "surname": "Ledgrave",
    "emailAddress": "martha.ledgrave@email.com", //optional
    "homeNumber": "07627847834", //optional
    "address": {
      "address1": "THE COTTAGE",
      "address2": "ST. MARYS ISLAND", // Optional
      "address3": "WHITLEY BAY",
      "address4": null, // Optional
      "postcode": "NE26 4RS"
    }
  },
  "payee": {
    "details": {
      "fullName": "INeed Paying",
      "emailAddress": "payment@now.com"
    },
    "address": {
      "address1": "THE COTTAGE",
      "address2": "ST. MARYS ISLAND", // Optional
      "address3": "WHITLEY BAY",
      "postcode": "NE26 4RS"
    },
    "bankDetails": {
      "accountHolderName": "Ineed Paying",
      "sortCode": "000004",
      "accountNumber": "12345677"
    }
  }
}
```

#### Response

200 - Ok

```json5
{
    "fileId": "claim-forms/payee-information/ATW12006521/f461ad53-9465-4129-9dab-9a8b4bf98af5"
}
```


Maintainer Team: Bluejay

Contributing file: ../CONTRIBUTING.md