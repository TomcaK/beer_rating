package cz.tomaskopulety.beer_rating.api.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class RatingCreateRequest {

    @NotNull
    @Range(min = 1, max = 5)
    @Schema(description = "Rating of beer. Must be value between 1 and 5.", example = "3", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer value;

    @Nullable
    @Schema(description = "Rating note.", example = "Delicious beer.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String note;

}
