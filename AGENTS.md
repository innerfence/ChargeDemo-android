# Scope and layout

This public Inner Fence repository contains the `chargeapi` Android library
and a separate demonstration app. When included as a submodule, also follow
the parent project's applicable guidance. Inspect Git status and diff in this
repository before editing, and preserve existing changes. Do not commit unless
requested.

`chargeapi/src/main/java/com/innerfence/chargeapi/ChargeRequest.java` and
`ChargeResponse.java` define the public Intent/Bundle payment contract. Preserve
parameter names, response codes, amount semantics, request codes, and the explicit
`com.innerfence.ccterminal.TerminalActivity` component. The `app/` module shows
the caller flow; changes there do not change the terminal app.

Keep maintenance changes scoped and follow the existing Java/XML conventions.
Use [README.markdown](README.markdown) for integration examples and field details;
check the source when changing the contract.

# Build and verification

Use JDK 17, Gradle 8.11.1, AGP 8.10.1, SDK 36, and build-tools 36.1.0.
Use current Platform-Tools (verified with 37.0.1); minimum API remains 30.
From this repository's directory:

```sh
./gradlew :chargeapi:assembleDebug :app:assembleDebug
./gradlew :chargeapi:lintDebug :app:lintDebug
```
