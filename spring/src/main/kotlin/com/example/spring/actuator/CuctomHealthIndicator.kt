package com.example.spring.actuator

import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.stereotype.Component

// HealthIndicatorを実装したクラスを作成すると、/actuator/healthのヘルスチェック対象リソースに追加される
// ```
// $ curl localhost:8080/actuator/health | jq .
// {
//   "status": "UP",
//   "components": {
//     "custom": {
//       "status": "UP",
//       "details": {
//         "detailMessage": "I'm fine"
//       }
//     },
//     "diskSpace": {
//       "status": "UP",
//       "details": {
//         "total": 494384795648,
//         "free": 397027487744,
//         "threshold": 10485760,
//         "path": "/repos/github.com/kei2100/playground-kotlin/.",
//         "exists": true
//       }
//     },
//     "ping": {
//       "status": "UP"
//     },
//     "ssl": {
//       "status": "UP",
//       "details": {
//         "validChains": [],
//         "invalidChains": []
//       }
//     }
//   }
// }
// ```
@Component
class CustomHealthIndicator : HealthIndicator {
    override fun health(): Health {
//        return Health.down()
//            .withDetail("errorReason", "Something went wrong")
//            .build()
        return Health.up()
            .withDetail("detailMessage", "I'm fine")
            .build()
    }
}