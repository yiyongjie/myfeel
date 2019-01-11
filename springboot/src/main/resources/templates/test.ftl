package ${classPath};
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "")
public class ${genContent.className} {

<#list genContent.genColumns as column>
   /*
   *${column.columnRemark}
   */
   @ApiModelProperty(value = "${column.columnRemark}")
   private ${column.colunmType} ${column.modelColumn};
</#list>
}