package kr.co.jhta.bang.finalproject.file;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        UploadFile file = (UploadFile) target;
        MultipartFile mf = file.getFile();

        int fileSize = 1024*1024*100;

        if(mf.getSize() == 0 ){
            errors.rejectValue("file", null, "파일을 선택해주세요");
        }else if(mf.getSize()>fileSize){
            errors.rejectValue("file", null, "10M이하 파일만 전송가능합니다.");
        }
    }
}
