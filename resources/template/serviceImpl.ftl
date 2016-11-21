package ${packageName};

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${classPackage}.${className};
import java.util.List;
import ${mapperPakage}.${className}Mapper;
import ${servicePackage}.${className}Service;
/**
 * 
 * @desc ${entityNameCN}service接口实现类
 * @author ${author}
 * @${date}
 */
@Service("${classNameLitter}Service")
public class ${className}ServiceImpl implements ${className}Service {

	@Autowired
	private ${className}Mapper ${classNameLitter}Mapper;

}