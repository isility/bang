package kr.co.jhta.bang.finalproject.file;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class UploadFile {
    private MultipartFile file;
}
