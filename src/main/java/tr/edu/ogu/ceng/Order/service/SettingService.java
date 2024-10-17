package tr.edu.ogu.ceng.Order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.edu.ogu.ceng.Order.repository.SettingRepository;

@Service
@RequiredArgsConstructor

public class SettingService {
    private SettingRepository settingRepository;
}
