package domain.my.doSports_Bot.configs;

import com.google.inject.Singleton;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;

@Data
@Singleton
public class ProjectProperties {

  @Inject
  @Named("db.admin.username")
  String dbAdminUsername;

  @Inject
  @Named("db.admin.password")
  String dbAdminPassword;

  @Inject
  @Named("db.url")
  String dbUrl;

}
