package com.example.DTM.validator;

import com.example.DTM.dto.member.MemberSignupDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SignupValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return MemberSignupDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberSignupDTO dto = (MemberSignupDTO) target;

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "password.mismatch", "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }
    }
}
