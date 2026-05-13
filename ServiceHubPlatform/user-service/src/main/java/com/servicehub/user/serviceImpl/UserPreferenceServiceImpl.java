package com.servicehub.user.serviceImpl;

import com.servicehub.user.POJO.UserPreference;
import com.servicehub.user.dao.UserPreferenceDao;
import com.servicehub.user.dto.UserPreferenceRequest;
import com.servicehub.user.dto.UserPreferenceResponse;
import com.servicehub.user.service.UserPreferenceService;
import com.servicehub.user.utils.JwtUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPreferenceServiceImpl implements UserPreferenceService {

    private final UserPreferenceDao dao;

    @Override
    public UserPreferenceResponse getPreference() {

        Long authUserId = JwtUserContext.getAuthUserId();

        UserPreference preference = dao.findByAuthUserId(authUserId)
                .orElseGet(() -> createDefaultPreference(authUserId));

        return map(preference);
    }

    @Override
    public UserPreferenceResponse updatePreferences(UserPreferenceRequest request) {
        Long authUserId = JwtUserContext.getAuthUserId();

        UserPreference preference = dao.findByAuthUserId(authUserId)
                .orElseGet(() -> createDefaultPreference(authUserId));

        preference.setPreferredLanguage(request.getPreferredLanguage());
        preference.setNotificationEnabled(request.isNotificationEnabled());
        preference.setServiceRadiusKm(request.getServiceRadiusKm());
        preference.setAutoAssignProvider(request.isAutoAssignProvider());
        preference.setPreferredServiceTime(request.getPreferredServiceTime());
        preference.setPreferredPaymentMode(request.getPreferredPaymentMode());
        preference.setAllowCall(request.isAllowCall());
        preference.setAllowWhatsapp(request.isAllowWhatsapp());

        dao.save(preference);

        return map(preference);
    }

    private UserPreference createDefaultPreference(Long authUserId){
        UserPreference pref = new UserPreference();
        pref.setAuthUserId(authUserId);
        pref.setPreferredLanguage("EN");
        pref.setNotificationEnabled(true);
        pref.setServiceRadiusKm(5.0);
        pref.setAutoAssignProvider(false);
        pref.setPreferredServiceTime("ANYTIME");
        pref.setPreferredPaymentMode("CASH");
        pref.setAllowCall(true);
        pref.setAllowWhatsapp(true);
        return dao.save(pref);
    }

    private UserPreferenceResponse map(UserPreference pref) {
        return UserPreferenceResponse.builder()
                .preferredLanguage(pref.getPreferredLanguage())
                .notificationEnabled(pref.isNotificationEnabled())
                .serviceRadiusKm(pref.getServiceRadiusKm())
                .autoAssignProvider(pref.isAutoAssignProvider())
                .preferredServiceTime(pref.getPreferredServiceTime())
                .preferredPaymentMode(pref.getPreferredPaymentMode())
                .allowCall(pref.isAllowCall())
                .allowWhatsapp(pref.isAllowWhatsapp())
                .build();
    }
}
