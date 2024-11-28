package tr.edu.ogu.ceng.Order.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;
import tr.edu.ogu.ceng.Order.AbstractContainerBaseTest;
import tr.edu.ogu.ceng.Order.Service.SettingService;
import tr.edu.ogu.ceng.Order.entity.Setting;
import tr.edu.ogu.ceng.Order.repository.SettingRepository;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SettingServiceTest extends AbstractContainerBaseTest {

    @MockBean
    private SettingRepository settingRepository; // Mock'lanan SettingRepository

    @Autowired
    private SettingService settingService; // SettingService'e otomatik olarak mock'lar enjekte edilir

    private Setting setting;

    @BeforeEach
    void setUp() {
        setting = new Setting();
        setting.setKey("app.version");
        setting.setValue("1.0.0");
    }

    @Test
    void testCreateSetting() {
        // Setting nesnesini mock repository'den kaydedildiğini simüle et
        when(settingRepository.save(any(Setting.class))).thenReturn(setting);

        // SettingService'in createSetting metodunu test et
        Setting createdSetting = settingService.createSetting("app.version", "1.0.0");

        // Sonuçların beklenen değerlerle uyumlu olup olmadığını kontrol et
        assertNotNull(createdSetting);
        assertEquals("app.version", createdSetting.getKey());
        assertEquals("1.0.0", createdSetting.getValue());

        // save() metodunun doğru bir şekilde çağrıldığını kontrol et
        verify(settingRepository, times(1)).save(any(Setting.class));
    }

    @Test
    void testUpdateSetting() {
        // Öncelikle mock'un findById metodunun ayarlanması
        when(settingRepository.findById(1L)).thenReturn(java.util.Optional.of(setting));
        when(settingRepository.save(any(Setting.class))).thenReturn(setting);

        // SettingService'in updateSetting metodunu test et
        Setting updatedSetting = settingService.updateSetting(1L, "app.version", "1.1.0");

        // Sonuçların beklenen değerlerle uyumlu olup olmadığını kontrol et
        assertNotNull(updatedSetting);
        assertEquals("app.version", updatedSetting.getKey());
        assertEquals("1.1.0", updatedSetting.getValue());

        // save() metodunun doğru bir şekilde çağrıldığını kontrol et
        verify(settingRepository, times(1)).save(any(Setting.class));
    }

    @Test
    void testDeleteSetting() {
        // SettingService'in deleteSetting metodunu test et
        doNothing().when(settingRepository).deleteById(1L);

        settingService.deleteSetting(1L);

        // deleteById() metodunun doğru bir şekilde çağrıldığını kontrol et
        verify(settingRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateSetting_notFound() {
        // findById() metodu, veri bulunamadığında IllegalArgumentException fırlatacak
        when(settingRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        // SettingService'in updateSetting metodunun hatalı durumda çalıştığını test et
        assertThrows(IllegalArgumentException.class, () -> {
            settingService.updateSetting(1L, "app.version", "2.0.0");
        });
    }
}
