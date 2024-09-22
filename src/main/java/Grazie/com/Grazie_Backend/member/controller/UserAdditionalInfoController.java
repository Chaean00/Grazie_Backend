package Grazie.com.Grazie_Backend.member.controller;

import Grazie.com.Grazie_Backend.Config.UserAdapter;
import Grazie.com.Grazie_Backend.member.dto.UserAdditionalInfoDTO;
import Grazie.com.Grazie_Backend.member.entity.UserAdditionalInfo;
import Grazie.com.Grazie_Backend.member.service.ImageStorageService;
import Grazie.com.Grazie_Backend.member.service.UserAdditionalInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/users/additional-info")
@Controller
@RequiredArgsConstructor
public class UserAdditionalInfoController {

    private final ImageStorageService imageStorageService;
    private final UserAdditionalInfoService userAdditionalInfoService;
    private final ObjectMapper objectMapper;



    @PostMapping("/{userId}/additionalInfoJoin")
    public ResponseEntity<UserAdditionalInfo> additionalInfoJoin(
            @PathVariable("userId") Long userId,
            @RequestParam("additionalInfo") String additionalInfoJson,
            @RequestPart("profileImage") MultipartFile profileImageFile) {

        try {
            UserAdditionalInfo userAdditionalInfo = objectMapper.readValue(additionalInfoJson, UserAdditionalInfo.class);
            UserAdditionalInfo saveAdditionalInfo = userAdditionalInfoService.saveAdditionalInfo(userId, userAdditionalInfo, profileImageFile);
            return ResponseEntity.ok(saveAdditionalInfo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("/readProfile")
    public ResponseEntity<?> readUserProfile(@AuthenticationPrincipal UserAdapter userAdapter) {
        try {
            UserAdditionalInfoDTO userAdditionalInfo = userAdditionalInfoService.readAdditionalInfo(userAdapter.getUser().getUserId());
            return ResponseEntity.ok(userAdditionalInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("프로필 조회 중 오류 발생");

        }
    }

    // 사진을 제외하고 업데이트
    @PutMapping("/update")
    public ResponseEntity<?> updateProfile(@RequestBody UserAdditionalInfoDTO userAdditionalInfoDTO, @AuthenticationPrincipal UserAdapter userAdapter) {
        try {
            UserAdditionalInfo userAdditionalInfo = userAdditionalInfoService.updateAdditionalInfo(userAdapter.getUser().getUserId(), userAdditionalInfoDTO);
            return ResponseEntity.ok(userAdditionalInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("프로필 업데이트 중 오류 발생");
        }
    }

    @PutMapping("/updateProfileImage")
    public ResponseEntity<?> updateProfileImage(@RequestPart("profileImage") MultipartFile profileImageFile, @AuthenticationPrincipal UserAdapter userAdapter) {
        try {
            UserAdditionalInfo additionalInfo = imageStorageService.updateImage(userAdapter.getUser().getUserId(), profileImageFile);
            return ResponseEntity.ok(additionalInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("프로필 이미지 업데이트 중 오류 발생");
        }
    }
}
