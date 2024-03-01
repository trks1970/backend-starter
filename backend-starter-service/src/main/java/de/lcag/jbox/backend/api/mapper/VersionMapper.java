package de.lcag.jbox.backend.api.mapper;

import de.lcag.jbox.backend.api.resource.VersionResource;
import org.springframework.stereotype.Component;

@Component
public class VersionMapper {
  private static final Integer MAJOR = 0;
  private static final Integer MINOR = 1;
  private static final Integer PATCH = 2;
  public VersionResource toVersion(String versionString){
    String[] splitVersion = versionString.split("-");
    String[] semVer = splitVersion[0].split("\\.");
    return new VersionResource()
        .major(Integer.valueOf(semVer[MAJOR]))
        .minor(Integer.valueOf(semVer[MINOR]))
        .patch(Integer.valueOf(semVer[PATCH]))
        .build(splitVersion[1]);

  }
}
