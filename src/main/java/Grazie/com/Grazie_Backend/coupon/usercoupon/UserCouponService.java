package Grazie.com.Grazie_Backend.coupon.usercoupon;

import Grazie.com.Grazie_Backend.coupon.Coupon;
import Grazie.com.Grazie_Backend.coupon.discountcoupon.DiscountCouponRepository;
import Grazie.com.Grazie_Backend.coupon.productcoupon.ProductCouponRepository;
import Grazie.com.Grazie_Backend.member.entity.User;
import Grazie.com.Grazie_Backend.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserCouponService {

    private final UserCouponRepository userCouponRepository;

    private final UserRepository userRepository;

    private final DiscountCouponRepository discountCouponRepository;

    private final ProductCouponRepository productCouponRepository;

    @Transactional
    public void issueCoupon(Long userId, Long couponId, String couponType) {
        User user = getUserById(userId);
        Coupon coupon = getCouponByIdAndType(couponId, couponType);

        checkAlreadyIssued(user, coupon);

        UserCoupon userCoupon = createUserCoupon(user, coupon);
        userCouponRepository.save(userCoupon);
    }

    private Coupon getCouponByIdAndType(Long couponId, String couponType) {
        if ("DISCOUNT".equals(couponType)) {
            return discountCouponRepository.findById(couponId)
                    .orElseThrow(() -> new IllegalArgumentException("해당하는 할인 쿠폰이 없습니다!"));
        } else if ("PRODUCT".equals(couponType)) {
            return productCouponRepository.findById(couponId)
                    .orElseThrow(() -> new IllegalArgumentException("해당하는 상품 쿠폰이 없습니다!"));
        } else {
            throw new IllegalArgumentException("잘못된 쿠폰 타입입니다.");
        }
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다!"));
    }

    private void checkAlreadyIssued(User user, Coupon coupon) {
        boolean alreadyIssued = userCouponRepository.existsByUserAndCoupon(user, coupon);
        if (alreadyIssued) {
            throw new IllegalStateException("이미 쿠폰을 발급받으셨습니다!");
        }
    }

    private UserCoupon createUserCoupon(User user, Coupon coupon) {
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setUser(user);
        userCoupon.setCoupon(coupon);
        userCoupon.setIsUsed(false);
        userCoupon.setIssuedAt(LocalDate.now());
        userCoupon.setExpirationDate(coupon.getExpirationDate());
        return userCoupon;
    }
}


