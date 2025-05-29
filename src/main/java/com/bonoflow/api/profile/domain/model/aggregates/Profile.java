package com.bonoflow.api.profile.domain.model.aggregates;

import com.bonoflow.api.iam.domain.model.aggregates.User;
import com.bonoflow.api.profile.domain.model.commands.CreateProfileCommand;
import com.bonoflow.api.profile.domain.model.commands.UpdateProfileCommand;
import com.bonoflow.api.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
public class Profile extends AuditableAbstractAggregateRoot<Profile> {
    @NotNull(message = "First Name is required")
    @NotBlank(message = "First Name cannot be blank")
    private String firstName;
    @NotNull(message = "Last Name is required")
    @NotBlank(message = "Last Name cannot be blank")
    private String lastName;
    @NotNull(message = "Birth Date is required")
    @Past(message = "Birth Date must be in the past")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    private String description;
    private String photo;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Profile() {
    }

    public Profile(CreateProfileCommand command, User user){
        this.firstName = command.firstName();
        this.lastName = command.lastName();
        this.birthDate = command.birthDate();
        this.description = command.description();
        this.photo = command.photo();
        this.user = user;
    }

    public Profile update(UpdateProfileCommand command){
        this.firstName = command.firstName();
        this.lastName = command.lastName();
        this.birthDate = command.birthDate();
        this.description = command.description();
        this.photo = command.photo();
        return this;
    }

    public Long getUserId() {
        return this.user.getId();
    }


}