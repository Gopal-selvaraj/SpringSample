package com.springtest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
//import java.util.UUID;




import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

//import net.sf.json.JSONObject;






import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

//import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {
	private final static Logger mLogger = Logger.getLogger(HelloWorldController.class .getName()); 
	PersistenceManager pm = PMF.get().getPersistenceManager();
	
	{
		System.out.println("welcome to Controller");
	}

	// if( @RequestMapping(value="hello.com")!=null){
	//
	// }
	@RequestMapping(value = "registration")
	public ModelAndView redirect() {
		//String welcome = "welcome registration page";
		// System.out.println("welcome registration page");
		return new ModelAndView("hellopage", "welcome", null);
	}
	
	@RequestMapping(value = "login")
	public ModelAndView login() {
		//String welcome = "welcome login page";
		// System.out.println("welcome registration page");
		return new ModelAndView("login", "welcome", null);
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(HttpServletRequest request, HttpServletResponse res)
			throws JSONException, IOException {
		// System.out.println("welcome1 datastore page");
		SampleBeanClass obj = new SampleBeanClass();
		JSONTokener jt = new JSONTokener(new InputStreamReader(
				request.getInputStream()));
		JSONObject json = new JSONObject(jt);
		try {
			// JSONObject obj=new JSONObject();
			String name = (String) json.get("Name");
			String id = (String) json.get("Id");
			String age = (String) json.get("Age");

			// UUID uid = UUID.randomUUID();
			obj.setAge(age);
			obj.setName(name);
			obj.setId(id);
			//
			pm.makePersistent(obj);
			//
		} finally {
			pm.close();
		}
		return "index";
	}

	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "userprofile", method = RequestMethod.POST)
	public ModelAndView userProfile(HttpServletRequest request,
			HttpServletResponse Response,ModelMap model) throws IOException, JSONException {
		System.out.println("welcome1");
		
		JSONTokener jt = new JSONTokener(new InputStreamReader(
				request.getInputStream()));
		JSONObject json = new JSONObject(jt);

		String name = (String) json.get("Name");
		String id = (String) json.get("Id");
		
		System.out.println("Name :"+name);

		try {
			// int increment = 0;

			// Query filter is used to filter the particular field with
			// value
			Query query = pm.newQuery(SampleBeanClass.class);
			query.setFilter("name =='" + name + "' ");
			System.out.println("welcome2");
			// using the List to get the entries from the data store
			List<SampleBeanClass> obj1 = (List<SampleBeanClass>) query
					.execute();
			for (SampleBeanClass sbo : obj1) {
				if (sbo.getName().equals(name) && sbo.getId().equals(id)) {
					
					model.addAttribute("Name", sbo.getName());
					model.addAttribute("Id", sbo.getId());
					model.addAttribute("Age", sbo.getAge());
					model.addAttribute("response", "success");
					
					System.out.println("Id :"+sbo.getId());
				}
			}
		} catch(Exception e){
//			mLogger.log(Level.WARNING, e.getMessage() , e);
//			model.addAttribute("response", "failure");
//			model.addAttribute("message", e.getMessage());
		}finally {
			pm.close();
		}
		return new ModelAndView("userprofile","profile",model);
	}

}
