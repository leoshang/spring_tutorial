package accounts.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.util.UriTemplate;

import accounts.Account;
import accounts.AccountManager;
import accounts.Beneficiary;

/**
 * A controller handling requests for CRUD operations on Accounts and their Beneficiaries.
 */
@Controller
public class AccountController {
	
	private AccountManager accountManager;
	
	/**
	 * Creates a new AccountController with a given account manager.
	 */
	@Autowired 
	public AccountController(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
	
	/**
	 * Provide a list of all accounts.
	 */
	// TODO 01: complete this method by adding the appropriate annotations to respond
	//          to a GET to /accounts and return a List<Account> to be converted
    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public List<Account> accountSummary() {
		return accountManager.getAllAccounts();
	}
	
	/**
	 * Provide the details of an account with the given id.
	 */
	// TODO 03: complete this method by adding the appropriate annotations to respond
	//          to a GET to /accounts/{accountId} and return an Account to be converted
    @RequestMapping(value = "/accounts/{accountId}", method = RequestMethod.GET)
	public Account accountDetails(@PathVariable("accountId") int id) {
		return retrieveAccount(id);
	}
	
	/**
	 * Creates a new Account, setting its URL as the Location header on the response.
	 */
	// TODO 05: complete this method by adding the appropriate annotations to respond to a
	//          POST to /accounts containing a marshalled Account with a 201 Created status
	@RequestMapping(value = "/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(Account newAccount,
							  HttpServletRequest request, 
							  HttpServletResponse response) {
		Account account = accountManager.save(newAccount);
		// TODO 06: set the Location header on the Response to the location of the created account
	    response.addHeader("location", getLocationForChildResource(request, account.getNumber()));
    }

	/**
	 * Returns the Beneficiary with the given name for the Account with the given id.   
	 */
	@RequestMapping(value="/accounts/{accountId}/beneficiaries/{beneficiaryName}", method=RequestMethod.GET)
	public @ResponseBody Beneficiary getBeneficiary(@PathVariable("accountId") int accountId, 
			                                        @PathVariable("beneficiaryName") String beneficiaryName) {
		return retrieveAccount(accountId).getBeneficiary(beneficiaryName);
	}

	/**
	 * Adds a Beneficiary with the given name to the Account with the given id,
	 * setting its URL as the Location header on the response. 
	 */
	// TODO 09: complete this method by adding the appropriate annotations to respond to a
	//          POST to /accounts/{accountId}/beneficiaries containing a beneficiary name
	//          with a 201 Created status
	@RequestMapping(value = "/accounts/{accountId}/beneficiaries")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBeneficiary(@PathVariable("accountId") int accountId,
							   String beneficiaryName,
							   HttpServletRequest request, 
							   HttpServletResponse response) {
		accountManager.addBeneficiary(accountId, beneficiaryName);
		// TODO 10: set the Location header on the Response to the location of the created beneficiary
        response.addHeader("location", getLocationForChildResource(request, beneficiaryName));
	}
	
	/**
	 * Removes the Beneficiary with the given name from the Account with the given id. 
	 */
	// TODO 11: complete this method by adding the appropriate annotations to respond to a
	//          DELETE to /accounts/{accountId}/beneficiaries/{beneficiaryName}
	//          with a 204 No Content status
    @RequestMapping("/accounts/{accountId}/beneficiaries/{beneficiaryName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeBeneficiary(@PathVariable("accountId") int accountId,
			                      @PathVariable("beneficiaryName") String beneficiaryName) {
		accountManager.removeBeneficiary(accountId, beneficiaryName);
	}
	
	/**
	 * Maps IllegalArgumentExceptions to a 404 Not Found HTTP status code.
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({IllegalArgumentException.class})
	public void handleNotFound() {
		// just return empty 404
	}

	// TODO 16 (bonus): add a new exception-handling method that maps
	// DataIntegrityViolationExceptions to a 409 Conflict status code.

	
	/**
	 * Finds the Account with the given id, throwing an IllegalArgumentException
	 * if there is no such Account. 
	 */
	private Account retrieveAccount(int accountId) throws IllegalArgumentException {
		Account account = accountManager.getAccount(accountId);
		if (account == null) {
			throw new IllegalArgumentException("No such account with id " + accountId);
		}
		return account;
	}

	/**
	 * determines URL of child resource based on the full URL of the given request,
	 * appending the path info with the given childIdentifier using a UriTemplate.
	 */ 
	private String getLocationForChildResource(HttpServletRequest request, Object childIdentifier) {
		StringBuffer url = request.getRequestURL();
		UriTemplate template = new UriTemplate(url.append("/{childId}").toString());
		return template.expand(childIdentifier).toASCIIString();
	}

}