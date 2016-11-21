package ${packageName};

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ${classPackage}.${className};
import ${servicePackage}.${className}Service;
import com.atclound.daizhang.api.BaseController;

/**
 * 
 * @desc ${entityNameCN}controller
 * @author ${author}
 * @${date}
 */

@Controller
@RequestMapping("${baseRequestMapping}")
public class ${controllerOtherName}Controller extends BaseController{ 

	@Autowired
	private ${className}Service ${classNameLitter}Service;

}

