package gov.gdgs.zs.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HtmlController {

	@RequestMapping(value = "/html", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
}
