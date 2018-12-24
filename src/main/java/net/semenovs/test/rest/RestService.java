package net.semenovs.test.rest;

import net.semenovs.test.model.Account;
import net.semenovs.test.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RestService {

    private ApplicationService service;

    @Autowired
    public RestService(ApplicationService service) {
        this.service = service;
    }

    @ResponseBody
    @RequestMapping(value = "createAccount", method = RequestMethod.POST)
    public ResponseEntity<CommonResponse> createAccount(@Valid @RequestBody CreateAccountRequest param) {
        Account account = service.createAccount(param.getUserName());
        CommonResponse response = new CommonResponse(CommonResponse.Status.SUCCESS, account);
        response.setMessage(String.format("Account created: [%s]", account.getUserName()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "transfer", method = RequestMethod.POST)
    public ResponseEntity<CommonResponse> transfer(@Valid @RequestBody TransferRequest request) {
        Account account = service
                .transfer(request.getSenderUserName(), request.getReceiverUserName(), request.getAmount());
        CommonResponse response = new CommonResponse(CommonResponse.Status.SUCCESS, account);
        String message = String
                .format("Transfer: amount [%s] from the user [%s] to the user [%s]", request.getAmount(),
                        request.getSenderUserName(), request.getReceiverUserName());
        response.setMessage(message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "topUp", method = RequestMethod.POST)
    public ResponseEntity<CommonResponse> topUp(@Valid @RequestBody AccountRequest request) {
        Account account = service.topUp(request.getUserName(), request.getAmount());
        String message = String.format("Top up: [%s]", request.getAmount());
        CommonResponse response = new CommonResponse(CommonResponse.Status.SUCCESS, account);
        response.setMessage(message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "withdraw", method = RequestMethod.POST)
    public ResponseEntity<CommonResponse> withdraw(@Valid @RequestBody AccountRequest request) {
        Account account = service.withdraw(request.getUserName(), request.getAmount());
        String message = String.format("Cash withdrawal: [%s]", request.getAmount());
        CommonResponse response = new CommonResponse(CommonResponse.Status.SUCCESS, account);
        response.setMessage(message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
