package name.nvshen.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TickController {
	
    public static int count = 0;
    
    @RequestMapping(value="/tick", method={RequestMethod.GET, RequestMethod.POST}, produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getTick() {
		
	    Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateStr = sdf.format(date) + " " + (++count);
	    System.out.println(dateStr);
	    
	    return dateStr;
	}
}
