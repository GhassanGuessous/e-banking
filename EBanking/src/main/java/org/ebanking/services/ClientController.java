package org.ebanking.services;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Secured(value = {"ROLE_CLIENT"})
public class ClientController {


}
