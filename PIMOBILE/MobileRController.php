<?php

namespace App\Controller;

use App\Entity\Reservation;
use App\Entity\Stade;
use App\Repository\StadeRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class MobileRController extends AbstractController
{
    /**
     * @Route("/mobile/r", name="mobile_r")
     */
    public function index(): Response
    {
        return $this->render('mobile_r/index.html.twig', [
            'controller_name' => 'MobileRController',
        ]);
    }

    /**
     * @Route("/addres", name="add_stade")
     */
    public function addStades(Request $request){
        $reservation= new Reservation();
        $idStade= $request->query->get("idStade");
        $heure= $request->query->get("heure");
        $name= $request->query->get("nom");
        $date= $request->query->get("date");
        $reservation->setNom($name);
        $reservation->setHeure($heure);
        $reservation->setIdStade($idStade);
        $reservation->setDate($date);

        $em=$this->getDoctrine()->getManager();
        $em->persist($reservation);
        $em->flush();
        $serialize = new Serializer([new ObjectNormalizer()]);
        $formatted = $serialize->normalize("Reservation Ajoutée");
        return new JsonResponse($formatted);

    }

}
