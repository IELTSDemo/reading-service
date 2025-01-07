window.onload = function() {
    // Получаем токен из URL (параметр token)
    const urlParams = new URLSearchParams(window.location.search);
    const token = urlParams.get('token');

    // Настраиваем Swagger UI
    window.ui = SwaggerUIBundle({
        url: "/reading-service/v3/api-docs", // Укажите путь к вашим API-документам
        dom_id: "#swagger-ui",
        presets: [
            SwaggerUIBundle.presets.apis,
            SwaggerUIStandalonePreset
        ],
        layout: "StandaloneLayout",
        requestInterceptor: (req) => {
            // Если токен есть, добавляем его в заголовок Authorization
            if (token) {
                req.headers["Authorization"] = `Bearer ${token}`;
            }
            return req;
        }
    });
};
