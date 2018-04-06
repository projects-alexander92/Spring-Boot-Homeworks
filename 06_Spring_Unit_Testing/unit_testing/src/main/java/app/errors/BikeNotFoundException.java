package app.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "page0222 not found")
public class BikeNotFoundException extends RuntimeException
{

}
