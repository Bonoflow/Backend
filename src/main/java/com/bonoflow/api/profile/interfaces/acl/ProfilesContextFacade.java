package com.bonoflow.api.profile.interfaces.acl;

import com.bonoflow.api.iam.domain.model.aggregates.User;
import com.bonoflow.api.profile.domain.model.aggregates.Profile;
import com.bonoflow.api.profile.domain.model.entities.Client;
import com.bonoflow.api.profile.domain.model.queries.GetClientByIdQuery;
import com.bonoflow.api.profile.domain.model.queries.GetProfileByUserIdQuery;
import com.bonoflow.api.profile.domain.services.ClientCommandService;
import com.bonoflow.api.profile.domain.services.ClientQueryService;
import com.bonoflow.api.profile.domain.services.ProfileQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfilesContextFacade {

    private final ProfileQueryService profileQueryService;
    private final ClientCommandService clientCommandService;
    private final ClientQueryService clientQueryService;

    public ProfilesContextFacade(ProfileQueryService profileQueryService,
                                 ClientCommandService clientCommandService,
                                 ClientQueryService clientQueryService) {
        this.profileQueryService = profileQueryService;
        this.clientCommandService = clientCommandService;
        this.clientQueryService = clientQueryService;
    }

    public Optional<Profile> fetchProfileByUserId(Long userId) {
        return profileQueryService.handle(new GetProfileByUserIdQuery(userId));
    }

    public Optional<Client> fetchClientById(Long farmerId) {
        var getClientByIdQuery = new GetClientByIdQuery(farmerId);
        return clientQueryService.handle(getClientByIdQuery);
    }

    public Optional<Profile> fetchProfileByClientId(Long clientId) {
        var clientProfileQuery = new GetClientByIdQuery(clientId);
        var client = clientQueryService.handle(clientProfileQuery);
        if (client.isEmpty()) return Optional.empty();
        Long userId = client.get().getUserId();
        var profileQuery = new GetProfileByUserIdQuery(userId);
        return profileQueryService.handle(profileQuery);
    }

    public Long createClient(Long userId, User user) {
        return clientCommandService.handle(new com.bonoflow.api.profile.domain.model.commands.CreateClientCommand(userId), user);
    }
}