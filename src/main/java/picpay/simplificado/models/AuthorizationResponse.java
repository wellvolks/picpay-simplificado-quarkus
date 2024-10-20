package picpay.simplificado.models;

public class AuthorizationResponse {
    private String status;
    private AuthorizationData data;

    // Getters e Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AuthorizationData getData() {
        return data;
    }

    public void setData(AuthorizationData data) {
        this.data = data;
    }

    public static class AuthorizationData {
        private boolean authorization;

        // Getters e Setters
        public boolean isAuthorization() {
            return authorization;
        }

        public void setAuthorization(boolean authorization) {
            this.authorization = authorization;
        }
    }
}