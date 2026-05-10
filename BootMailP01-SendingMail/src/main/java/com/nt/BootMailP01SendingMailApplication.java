package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.nt.service.IShoppingService;

@SpringBootApplication
public class BootMailP01SendingMailApplication {

	public static void main(String[] args) {
		// get IOC container
		try (ConfigurableApplicationContext ctx = SpringApplication.run(BootMailP01SendingMailApplication.class,
				args)) {
			// get Spring bean class obj ref
			IShoppingService service = ctx.getBean("sms", IShoppingService.class);
			// invoke the b.method
			try {
				String resultMsg = service.purchase(new String[] { "shirt", "trouser", "hat" },
						new double[] { 2000.0, 3000.0, 1000.0 },
						new String[] { "mahesh3q@gmail.com", "encryptedcv@gmail.com", "mahesh3w@outlook.com" });

				System.out.println(resultMsg);

			} // try2
			catch (Exception e) {
				e.printStackTrace();
			}
		} // try1
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
