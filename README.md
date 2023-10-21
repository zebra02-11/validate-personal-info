# Person number and name validators


Validate Swedish personal identity number and personal name.

### Validation
#### The requirements for validating a swedish personal number:
- 10 or 12 digits
- Can include hyphen or plus sign
- Calculate and validate checksum using the Luhn algorithm

#### The requirements for validating a personal name:
- Not blank
- Swedish alphabetical characters including swedish å, ä and Ö

The SSN verification logic can be found in `src/main/java/personalinfovalidation/SsnValidator.java`
The basic username verification logic can be found in `src/main/java/personalinfovalidation/NameValidator.java`


### Supported formats:
- Short format (YYMMDD-XXXX)
- Short format for 100+ years old(YYMMDD+XXXX)
- Long format (YYYYMMDDXXXX)
- Long format (YYYYMMDD-XXXX)



See `src/test/java/personalinfovalidation/SsnValidatorTest.java` for examples of personal number validation.
See `src/test/java/personalinfovalidation/NameValidatorTest.java` for examples of personal name validation.
