Interswitch Funds Transfer for Java.
====
[Support chat](https://gitter.im/techquest)

This project is based on interswitch base [Interswitch](https://github.com/techquest/interswitch_java) library.

| JDK Version compatibility |
| ------------- | ------------- |
|1.8+, 1.7, 1.6 |

Features
================================

* Fetch all banks

	Get a list of all banks supported on our platform.

* Account Validation
  
  	Validates an account number against a particular bank. If the account number was registered under the bank,
  	it returns a successful response. Otherwise, it returns a failed response.

* Funds Transfer.

	Transfers funds from a senders account to receivers account.
	
Quick start
===============================

#### Maven 
    <!-- JDK 1.6+ compatible -->
    <dependency>
        <groupId>com.interswitch</groupId>
        <artifactId>interswitch-java</artifactId>
        <version>0.0.1</version>
    </dependency>
    <dependency>
    	<groupId>com.google.code.gson</groupId>
    	<artifactId>gson</artifactId>
    	<version>2.6.2</version>
	</dependency>

#### Fetch All Banks Request

```java

import com.interswitch.techquest.auth.Interswitch;
import com.interswitch.transfer.FundsTransfer;

private static final String initiatingEntityCode = "XXT";

private final static String clientId = "IKIA6570778A3484D6F33BC7E4165ADCA6CF06B2860A";
private final static String clientSecret = "DXfUwpuIvMAKN84kv38uspqGOsStgFS0oZMjU7bPwpU=";

public static void main(String[] args) {

	FundsTransfer transfer = new FundsTransfer(clientId, clientSecret, Interswitch.ENV_SANDBOX);
	
	try {

        BankResponse bankResponse = transfer.fetchBanks();

        Bank[] bank = bankResponse.getBanks(); // a bank array of all banks

        if (bank instanceof Object) {

            // successful
            Bank testBank = bank[0]; // bank at index 0

            String cbnCode = testBank.getCbnCode(); // Central bank code
            String bankName = testBank.getBankName(); // bank name:
            String bankCode = testBank.getBankCode(); // bankcode in alphabetical form: UBA, GTB, FBN
    	 }
    }
    catch(Exception ex) {
    
    }
}
```

#### Accounts Validation Request