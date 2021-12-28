package az.elvin.construction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.nio.ByteBuffer;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {

    //    private byte[] mainImage;
    private ByteBuffer mainImage;

    private String mainImageName;

//    private MultipartFile firstImage;
//
//    private MultipartFile secondImage;

}
