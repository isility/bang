package kr.co.jhta.bang.finalproject.file;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component // Spring 컴포넌트로 등록
public class FileValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UploadFile.class.isAssignableFrom(clazz); // UploadFile 클래스 또는 그 하위 클래스를 지원
    }

    @Override
    public void validate(Object target, Errors errors) {
        UploadFile file = (UploadFile) target;

        MultipartFile mf = file.getFile();

        int fileSize = 1024 * 1024 * 100;

        if (mf.getSize() == 0) {
            errors.rejectValue("file", null, "파일을 선택해주세요.");
        } else if (mf.getSize() > fileSize) {
            errors.rejectValue("file", null, "100MB 이하의 파일만 전송 가능합니다.");
        }
    }
}
