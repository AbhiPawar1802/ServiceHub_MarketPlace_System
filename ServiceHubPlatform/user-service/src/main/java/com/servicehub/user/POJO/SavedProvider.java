package com.servicehub.user.POJO;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "saved_providers", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"auth_user_id", "provider_id"})
}
)
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavedProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long authUserId;

    @Column(name = "providerId", nullable = false)
    private String providerId;

    @Column(name = "providerName")
    private String providerName;

    @Column(name = "savedAt")
    private LocalDateTime savedAt;

    @PrePersist
    protected void onSave(){
        this.savedAt= LocalDateTime.now();
    }

}
