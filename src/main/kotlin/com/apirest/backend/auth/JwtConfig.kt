package com.apirest.backend.auth

class JwtConfig

const val KEY_SECRETE = "key.secrete.12345678"

const val RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\n" +
        "MIIEpAIBAAKCAQEAtSr9uK/YgjyseVmKwpNNOYiFkLeFewVx1JoQWXi/GUu/n8em\n" +
        "9eyMlUGdo3wj83fk37S/GqwE6DsuDhKrY1RgC7DPivteP/6tw0+Fs2LqHekSV3ab\n" +
        "LVpCg270RnxEz4TdW9viJJHxWcdC5Acr4boFZQRSdf66cELr33hK7nNaMRf6N74j\n" +
        "DE+coJWE3lp/gGUAxOssL4rq13SIDIhVTC67aXM3sTNsxvNX5c/azrm1h/QM4hLc\n" +
        "eVwJIgzyXbQDSIhOz5ufO6XzdAM2/1z3DT6hoYtU7K2EJz/Ild7Jtv27JSLBr2fj\n" +
        "ahQVPReZoNiJX1Ui989zEoGUoaKvyCd8iY2AEwIDAQABAoIBAGu9sHxoX2s8qBnW\n" +
        "7ZW/wlpn3X7uFXzPBR45rWV4ULW7fa+JLbrtPcH57sTuK/c8IyoDNO+ow5ZUs5P8\n" +
        "+F4vLJtnQFZYHXoTfMU9grbnKXI2TQkk6S40kn/VuYI9nzKGwppBjyDOhmks/pPb\n" +
        "y/YHCfREeMHhhoEC24Hf6Tgb89Rje9tAdmaKz9Xtraa/PSZ0eJCinK4b9AQB/Muy\n" +
        "c0BbxoRwlQPnWCYkH+GKK5HuS49/of8vtcwHWkluPYaYxe0pR6uNZeZ4guQGFEB0\n" +
        "l46QzYcEChHB/Q5lNPGq53VF5gKtZT8j0ZpXzv23uWXlFXDuEBkPq0CNyh3UFWmT\n" +
        "qUvUZCECgYEA6/jKn0M1J+DRrYYX3M2xPswI1R+NBu3T954taO8l32KliZWtJcmR\n" +
        "aEhTxRe/3YZ4JF4JxGlKRV9ZyVCV935QSS64DxKLFWkOOAb6bl7jEtf12vTeUpm7\n" +
        "iY8xrhgl26xATulq1gL6sgQdXSA3kna7m8ZvT6fYjWTNehRX6FCdy/ECgYEAxItq\n" +
        "3QJuIJdLVBtpg5le5Ryqyi/nLR3gzAi1eyCAODzaepAR/4RLPglWCKte4YLxttqb\n" +
        "ma7+zMYQ4JjlStpL9Tp+uuAK+5jBAzW6NthukNuOwamX74GCCygs8+TntRA6nYQ2\n" +
        "hSzDZky158lvc0k8al7MBVUR6BznKM3wajHzIEMCgYEA3OdDiGS6VmK45+EoZaSJ\n" +
        "0WfgvOOUenAahyylBh8bLUyD+5ELTtysKxvKkC/zhcZkomcGbcDDrr3cqAGw4sh1\n" +
        "EI2T8UhCvABlNz4RI1Hg8bsKhbfGiT0xu006DOi+SC+w5C7PvRl85znC43NNkudk\n" +
        "KihR/Bf9OiCr2yVARlNx5YECgYAZn5Nu/MIevZq0fF5lKbmVZQdfFJ+u4hZMg1Yl\n" +
        "lzcgHc7d66ptBXJJxC3TM4RLIichD575ijbtu2S34zcBazQ75ERhWJQV+PQw1vBP\n" +
        "9idz+yvBU7rbHqc1jSdPJKiQkm0R3Aq/kfZnf9i3PyuofAPEWld+2nhbKml4iGCv\n" +
        "d5OawQKBgQDnRtSxMNuaYWdKpxJkA1yWoyOZ8QLWnorzzKDfyOV8gaYhcyf67j/S\n" +
        "gOkBz0+P4KuIrfN6QmNeNMhYWlR9w65l18O6vX0tLTB/QFfgka6Qg8dVfgF79jTk\n" +
        "Vx/1Vq7tsx4rrNeC/vmP9zt6zlMnzVV9s1AzGW1d3nvIPR4FfkE4ow==\n" +
        "-----END RSA PRIVATE KEY-----"

const val RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\n" +
        "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtSr9uK/YgjyseVmKwpNN\n" +
        "OYiFkLeFewVx1JoQWXi/GUu/n8em9eyMlUGdo3wj83fk37S/GqwE6DsuDhKrY1Rg\n" +
        "C7DPivteP/6tw0+Fs2LqHekSV3abLVpCg270RnxEz4TdW9viJJHxWcdC5Acr4boF\n" +
        "ZQRSdf66cELr33hK7nNaMRf6N74jDE+coJWE3lp/gGUAxOssL4rq13SIDIhVTC67\n" +
        "aXM3sTNsxvNX5c/azrm1h/QM4hLceVwJIgzyXbQDSIhOz5ufO6XzdAM2/1z3DT6h\n" +
        "oYtU7K2EJz/Ild7Jtv27JSLBr2fjahQVPReZoNiJX1Ui989zEoGUoaKvyCd8iY2A\n" +
        "EwIDAQAB\n" +
        "-----END PUBLIC KEY-----"