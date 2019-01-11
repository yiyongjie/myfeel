package ${mapperClassPath};

import org.apache.ibatis.annotations.Mapper;
import ${modelClassPath}.${genContent.className};
import java.util.List;

@Mapper
public interface ${genContent.className}Mapper {
    int add${genContent.className}(${genContent.className} ${genContent.varName});
    int update${genContent.className}(${genContent.className} ${genContent.varName});
    int delete${genContent.className}(${genContent.className} ${genContent.varName});
    List<${genContent.className}> list${genContent.className}(${genContent.className} ${genContent.varName});
}