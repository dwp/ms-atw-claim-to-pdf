# Changelog

# 1.20.0 - (2024-08-21)
* Updated dependency Suppressions
* Updated distroless version
* Updated fragments

# 1.19.0 - (2024-07-16)
* Upgrade to Java 17
* Upgrade to Spring boot 3.2.5
* Updated dependency Suppressions
* Updated fragments

# 1.18.0 - (2024-06-04)
* Updated fragments
* Removed SAAS references
* Dependency suppression

# 1.17.0 - (2024-04-18)
* Updated fragments
* Dependency suppression
* Updated distroless version
* Adding new journey Travel in Work functionality

# 1.16.0 - (2024-02-13)
* Updated dependency versions
* Update fragments
* Updated distroless version
* Fix to temporarily support the old and new data model for support worker for how the nameOfSupport is received

# 1.15.0 - (2024-01-15)
* Updated dependency versions
* Update fragments
* Adding new journey Adaptation to Vehicle functionality

# 1.14.0 - (2023-10-30)
* Updated dependency versions
* Update distroless version
* Update fragments
* Update pipeline to v3.1

# 1.13.0 - (2023-08-02)
* Make logging configurable in application.yml
* Update schedule creation fragment

## 1.12.0 - (2023-07-11)
* Update spring logging for production
* Update fragment to latest version

## 1.11.0 - (2023-06-20)
* Update all fragments to latest version
* Update dependency versions
* Update cron schedule to run weekdays only

## 1.10.0 - (2023-05-26)
* adding open source functionality for a github repository
* Update all fragments to latest version
* Update dependency versions

## 1.9.0 - (2023-04-19)
* Fix to temporarily support the old and new data model for support worker received support which can be documented in hours or hours and minutes
* Update all fragments to latest

## 1.8.0 - (2023-04-11)
* Add minutes for support worker so support received can be documented in hours and minutes
* Update all fragments to latest
* Update dependency versions

## 1.7.0 - (2023-03-06)
* Update to model to use homeNumber and mobileNumber instead of phoneNumber
* Move to saas runners
* Update all fragments to latest
* Update dependency versions

## 1.6.0 - (2023-01-10)
* Made change to fix issue with £ character.
* Updated fragments to be the latest
* Distroless version bump
* OWASP version update (7.4.4)

## 1.5.0 - (2022-12-6)
* Made change to fix issue with & character.
* Made change to pdfs Create/Amend personal details and Create/Amend payee details form headers
* Updated fragments to be the latest

## 1.4.0 - (2022-11-8)

* Made changes to pdfs and pdf tests, they now read "Payee Details" rather than "Details of person or company being paid"
* Removed the following line from all pdfs "The amount we’ll pay back will be less than the claim total. 
This is because we’ll deduct any amounts the claimant or their employer have agreed to pay."
* Update Spring Boot version (2.7.5)
* Snakeyaml version update (1.33)
* Distroless version bump
* Update all fragments to latest

## 1.3.0 - (2022-10-11)

* Upgrade Spring Boot (2.7.3)
* Address Snakeyaml vulnerability in 1.30
* Update all fragments to latest

## 1.2.0 - (2022-08-19)

* Updates fragment version for trivy container
* Fixed generated pdfs content issues

## 1.1.0 - (2022-08-01)

* Release

## 1.0.0 - (2022-07-18)

* Initial release