package tr.edu.ogu.ceng.Order.RepositoryTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;
import tr.edu.ogu.ceng.Order.AbstractContainerBaseTest;
import tr.edu.ogu.ceng.Order.entity.Setting;
import tr.edu.ogu.ceng.Order.repository.SettingRepository;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SettingsRepositoryTest extends AbstractContainerBaseTest {

    @Autowired
    private SettingRepository repository;

    @Test
    public void testSaveAndFindByKey() {
        // Yeni ayar oluşturuluyor ve kaydediliyor
        Setting setting = new Setting();
        setting.setKey("theme");
        setting.setValue("dark");

        repository.save(setting);

        // Key'e göre ayar sorgulanıyor
        Setting foundSetting = (Setting) repository.findByKey("theme").orElse(null);

        // Ayar bulunduysa değeri kontrol ediliyor
        assertThat(foundSetting).isNotNull();
        assertThat(foundSetting.getValue()).isEqualTo("dark");
    }

    @Test
    public void testUpdateSetting() {
        // Yeni bir ayar oluşturuluyor ve kaydediliyor
        Setting setting = new Setting();
        setting.setKey("language");
        setting.setValue("English");

        Setting savedSetting = repository.save(setting);

        // Ayar güncelleniyor
        savedSetting.setValue("Turkish");
        repository.save(savedSetting);

        // Güncellenmiş ayar sorgulanıyor
        Setting updatedSetting = (Setting) repository.findByKey("language").orElse(null);

        // Güncellenmiş değerin doğru olduğu kontrol ediliyor
        assertThat(updatedSetting).isNotNull();
        assertThat(updatedSetting.getValue()).isEqualTo("Turkish");
    }
}
