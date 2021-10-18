package study.datajpa.dto;

import lombok.Data;

// Entity는 @Data는 getter/setter 모두 들어가 있어서 왠만하면 지양.
@Data
public class MemberDto {
    private Long id;
    private String username;
    private String teamName;

    public MemberDto(Long id, String username, String teamName) {
        this.id = id;
        this.username = username;
        this.teamName = teamName;
    }
}
