package ${classPath};
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "${classRemark}")
public class ${className} {

   @ApiModelProperty(value = "${remark}")
   private Integer ${content};
}