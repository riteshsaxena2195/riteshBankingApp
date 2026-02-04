# Build stage
FROM node:22-alpine AS build

WORKDIR /app

# This ARG is used to set the API URL for the React application
ARG REACT_APP_API_URL

# Inject the API URL into the environment variable
ENV REACT_APP_API_URL=$REACT_APP_API_URL

COPY package*.json ./
RUN npm ci --only=production
COPY . .

# Install dependencies and build the React application
RUN npm run build

# Production stage
FROM nginx:alpine AS production

COPY --from=build /app/build /usr/share/nginx/html

COPY nginx.conf /etc/nginx/conf.d/default.conf.template

RUN echo '#!/bin/sh' > /docker-entrypoint.sh && \
    echo 'envsubst "\$PORT" < /etc/nginx/conf.d/default.conf.template > /etc/nginx/conf.d/default.conf' >> /docker-entrypoint.sh && \
    echo 'exec nginx -g "daemon off;"' >> /docker-entrypoint.sh && \
    chmod +x /docker-entrypoint.sh

ENV PORT=80
EXPOSE $PORT

CMD ["/docker-entrypoint.sh"]
