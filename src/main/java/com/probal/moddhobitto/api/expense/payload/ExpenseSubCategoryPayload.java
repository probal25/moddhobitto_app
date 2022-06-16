package com.probal.moddhobitto.api.expense.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseSubCategoryPayload implements Serializable {

    private String name;

    @NotNull
    private Long parentCategoryId;

    private String description;

}
