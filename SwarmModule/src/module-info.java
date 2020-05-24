module SwarmModule {
    requires kotlin.stdlib;
    exports swarm;
    requires TopologyModule;
    requires FunctionModule;
    requires ParticleModule;
    requires kotlinx.coroutines.core;
}