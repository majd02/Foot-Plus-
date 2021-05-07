<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Livreur
 *
 * @ORM\Table(name="livreur")
 * @ORM\Entity(repositoryClass="App\Repository\LivreurRepository")
 */
class Livreur
{
    /**
     * @var int
     *
     * @ORM\Column(name="idLivreur", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idlivreur;

    /**
     * @var string
     *
     * @ORM\Column(name="nomLivreur", type="string", length=225, nullable=false)
     */
    private $nomlivreur;

    /**
     * @var string
     *
     * @ORM\Column(name="prenomLivreur", type="string", length=225, nullable=false)
     */
    private $prenomlivreur;

    /**
     * @var string
     *
     * @ORM\Column(name="email", type="string", length=225, nullable=false)
     */
    private $email;

    /**
     * @var int
     *
     * @ORM\Column(name="numTelephoneLivreur", type="integer", nullable=false)
     */
    private $numtelephonelivreur;

    /**
     * @var string
     *
     * @ORM\Column(name="adresseLivreur", type="string", length=255, nullable=false)
     */
    private $adresselivreur;

    public function getIdlivreur(): ?int
    {
        return $this->idlivreur;
    }

    public function getNomlivreur(): ?string
    {
        return $this->nomlivreur;
    }

    public function setNomlivreur(string $nomlivreur): self
    {
        $this->nomlivreur = $nomlivreur;

        return $this;
    }

    public function getPrenomlivreur(): ?string
    {
        return $this->prenomlivreur;
    }

    public function setPrenomlivreur(string $prenomlivreur): self
    {
        $this->prenomlivreur = $prenomlivreur;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }

    public function getNumtelephonelivreur(): ?int
    {
        return $this->numtelephonelivreur;
    }

    public function setNumtelephonelivreur(int $numtelephonelivreur): self
    {
        $this->numtelephonelivreur = $numtelephonelivreur;

        return $this;
    }

    public function getAdresselivreur(): ?string
    {
        return $this->adresselivreur;
    }

    public function setAdresselivreur(string $adresselivreur): self
    {
        $this->adresselivreur = $adresselivreur;

        return $this;
    }


}
